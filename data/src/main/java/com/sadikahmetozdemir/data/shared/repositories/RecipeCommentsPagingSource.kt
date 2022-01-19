package com.sadikahmetozdemir.data.shared.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadikahmetozdemir.data.mappers.toDomaninModel
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.entities.Recipe

class RecipeCommentsPagingSource(
    private var editorChoiceRecipesAPI: EditorChoiceRecipesAPI,
    private var categoryID: Int
) :
    PagingSource<Int, Comment>() {
    private val STARTING_PAGE_INDEX = 1

    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {

        val currentPage = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = editorChoiceRecipesAPI.getRecipeComments(categoryID, currentPage)
            val dataFavoriteCategories = response.data.map { it.toDomaninModel() }

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
