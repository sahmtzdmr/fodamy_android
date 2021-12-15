package com.sadikahmetozdemir.sadik_fodamy.shared.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadikahmetozdemir.sadik_fodamy.api.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.FavoritesCategoryModel

class FavoritesPagingSource (private var editorChoiceRecipesAPI: EditorChoiceRecipesAPI) :
    PagingSource<Int, FavoritesCategoryModel>() {
    private val STARTING_PAGE_INDEX = 1


    override fun getRefreshKey(state: PagingState<Int, FavoritesCategoryModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoritesCategoryModel> {
        val currentPage = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = editorChoiceRecipesAPI.favoriteRecipesRequest(currentPage)
            val dataLastAddedRecipes = response.data

            LoadResult.Page(
                data = dataLastAddedRecipes,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage.minus(
                    STARTING_PAGE_INDEX
                ),
                nextKey = if (dataLastAddedRecipes.isEmpty()) null else currentPage.plus(
                    STARTING_PAGE_INDEX
                )
            )
        }catch (expection:Exception){
            LoadResult.Error(expection)
        }


    }
}