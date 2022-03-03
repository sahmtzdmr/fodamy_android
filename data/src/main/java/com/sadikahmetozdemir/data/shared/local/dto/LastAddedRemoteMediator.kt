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
import java.io.IOException

@ExperimentalPagingApi
class LastAddedRemoteMediator(
    private val editorChoiceRecipesAPI: EditorChoiceRecipesAPI,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, RecipeDatabase>() {
    private val STARTING_PAGE_INDEX = 1

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, RecipeDatabase>
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

            if (loadType == LoadType.REFRESH) {
                 appDatabase.recipeDao().deleteLastAddeds()
                   appDatabase.remoteKeyDao().deleteLastAdded()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.data.map {
                    RemoteKeyLastAddedDatabase(it.id, prevKey, nextKey)

                }
                appDatabase.remoteKeyDao().insertLastAdded(keys)
                appDatabase.recipeDao().insertLastAdded(response.data.map {
                    it.toLocalDto(isLastAdded = true)
                })

            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, RecipeDatabase>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> INITIAL_LOAD
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                remoteKeys?.prevKey ?: MediatorResult.Success(remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                remoteKeys?.nextKey ?: MediatorResult.Success(remoteKeys != null)
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, RecipeDatabase>): RemoteKeyLastAddedDatabase? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                appDatabase.remoteKeyDao().remoteKeysLastAddedId(repoId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, RecipeDatabase>): RemoteKeyLastAddedDatabase? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { recipe ->
                appDatabase.remoteKeyDao().remoteKeysLastAddedId(recipe.id)
            }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, RecipeDatabase>): RemoteKeyLastAddedDatabase? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { recipe -> appDatabase.remoteKeyDao().remoteKeysLastAddedId(recipe.id) }
    }

    companion object {
        private const val INITIAL_LOAD = 1
    }

}