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
class EditorChoiceRemoteMediator(
    private val editorChoiceRecipesAPI: EditorChoiceRecipesAPI,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, RecipeDatabase>() {
    private val STARTING_PAGE_INDEX = 1

//    override suspend fun initialize(): InitializeAction {
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }

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
            val response = editorChoiceRecipesAPI.editorChoicesRecipesRequest(page)
            val isEndOfList = response.data.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.recipeDao().deleteEditorChoices()
                    appDatabase.remoteKeyDao().deleteEditorChoice()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.data.map {
                    RemoteKeyEditorChoiceDatabase(it.id, prevKey, nextKey)

                }
                appDatabase.remoteKeyDao().insertEditorChoice(keys)
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

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, RecipeDatabase>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                return nextKey ?: MediatorResult.Success(remoteKeys!=null)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    remoteKeys!=null
                )
                prevKey
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, RecipeDatabase>): RemoteKeyEditorChoiceDatabase? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                appDatabase.remoteKeyDao().remoteKeysEditorChoiceId(repoId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, RecipeDatabase>): RemoteKeyEditorChoiceDatabase? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { recipe ->
                appDatabase.remoteKeyDao().remoteKeysEditorChoiceId(recipe.id)
            }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, RecipeDatabase>): RemoteKeyEditorChoiceDatabase? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { recipe -> appDatabase.remoteKeyDao().remoteKeysEditorChoiceId(recipe.id) }
    }


}