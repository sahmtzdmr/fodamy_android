package com.sadikahmetozdemir.data.shared.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI

class RecipeCommentsPagingSource(
    private var editorChoiceRecipesAPI: EditorChoiceRecipesAPI,
    private var categoryID: Int
) :
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
            val response = editorChoiceRecipesAPI.getRecipeComments(categoryID, currentPage)
            val dataFavoriteCategories = response.data

            LoadResult.Page(
                data = dataFavoriteCategories,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage.minus(
                    STARTING_PAGE_INDEX
                ),
                nextKey = if (dataFavoriteCategories.isEmpty()) null else currentPage.plus(
                    STARTING_PAGE_INDEX
                )
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
