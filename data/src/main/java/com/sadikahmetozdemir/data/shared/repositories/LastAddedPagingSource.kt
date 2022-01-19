package com.sadikahmetozdemir.data.shared.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadikahmetozdemir.data.mappers.toDomaninModel
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.domain.entities.Recipe

class LastAddedPagingSource(private var editorChoiceRecipesAPI: EditorChoiceRecipesAPI) :
    PagingSource<Int, Recipe>() {
    private val STARTING_PAGE_INDEX = 1

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val currentPage = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = editorChoiceRecipesAPI.lastAddedRecipesRequest(currentPage)
            val dataLastAddedRecipes = response.data.map { it.toDomaninModel() }

            LoadResult.Page(
                data = dataLastAddedRecipes,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage.minus(
                    STARTING_PAGE_INDEX
                ),
                nextKey = if (dataLastAddedRecipes.isEmpty()) null else currentPage.plus(
                    STARTING_PAGE_INDEX
                )
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}