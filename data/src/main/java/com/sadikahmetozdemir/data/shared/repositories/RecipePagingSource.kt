package com.sadikahmetozdemir.data.shared.repositories
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadikahmetozdemir.data.mappers.toDomaninModel
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.domain.entities.Recipe

class RecipePagingSource(private var editorChoiceRecipesAPI: EditorChoiceRecipesAPI) :
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
            val response = editorChoiceRecipesAPI.editorChoicesRecipesRequest(currentPage)
            val dataRecipes = response.data.map { it.toDomaninModel() }

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