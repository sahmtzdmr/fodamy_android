package com.sadikahmetozdemir.data.shared.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository

class FavoriteCategoriesPagingSource(
    private var feedRepository: FeedRepository,
    private var categoryID: Int
) :
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
            val response =
                feedRepository.favoriteCategoriesRequest(categoryID, currentPage)
            val dataFavoriteCategories = response

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
