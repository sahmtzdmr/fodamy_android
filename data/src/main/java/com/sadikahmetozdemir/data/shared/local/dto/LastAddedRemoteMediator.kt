package com.sadikahmetozdemir.data.shared.local.dto

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bumptech.glide.load.HttpException
import com.sadikahmetozdemir.data.mappers.toLocalDto
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.data.shared.local.database.AppDatabase
import com.sadikahmetozdemir.domain.entities.Recipe
import java.io.IOException

@ExperimentalPagingApi
class LastAddedRemoteMediator(
    private val editorChoiceRecipesAPI: EditorChoiceRecipesAPI,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, Recipe>() {
    private val STARTING_PAGE_INDEX = 1

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, Recipe>
    ): MediatorResult {
        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val response = editorChoiceRecipesAPI.lastAddedRecipesRequest(page)
            val isEndOfList = response.data.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.recipeDao().deleteAll()
                    appDatabase.remoteKeyDao().deleteAll()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.data.map {
                    nextKey?.let { it1 ->
                        prevKey?.let { it2 ->
                            RemoteKey(
                                it.id.toString(),
                                prevKey = it2,
                                nextKey = it1
                            )
                        }
                    }
                }
                appDatabase.remoteKeyDao().insertAll(keys as List<RemoteKey>)
                appDatabase.recipeDao().insertRecipes(response.data.map {
                    it.toLocalDto()
                })
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, Recipe>): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                return nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Recipe>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                appDatabase.remoteKeyDao().remoteKeysId(repoId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, Recipe>): RemoteKey? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { recipe ->
                appDatabase.remoteKeyDao().remoteKeysId(recipe.id)
            }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, Recipe>): RemoteKey? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { recipe -> appDatabase.remoteKeyDao().remoteKeysId(recipe.id) }
    }


}