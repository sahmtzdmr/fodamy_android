package com.sadikahmetozdemir.sadik_fodamy.shared.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadikahmetozdemir.sadik_fodamy.api.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel

class RecipePagingSource(private var editorChoiceRecipesAPI: EditorChoiceRecipesAPI) :
    PagingSource<Int, EditorChoiceModel>() {
    private val STARTING_PAGE_INDEX = 1

    override fun getRefreshKey(state: PagingState<Int, EditorChoiceModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EditorChoiceModel> {
        val currentPage = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = editorChoiceRecipesAPI.editorChoicesRecipesRequest(currentPage)
            val dataRecipes = response.data

            LoadResult.Page(
                data = dataRecipes,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (dataRecipes.isEmpty()) null else currentPage + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
