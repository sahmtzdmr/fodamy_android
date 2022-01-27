package com.sadikahmetozdemir.data.shared.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadikahmetozdemir.data.mappers.toDomainModel
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import kotlinx.coroutines.flow.Flow

class FavoritesPagingSource(private var feedRepository: FeedRepository) :
    PagingSource<Int,Category>() {
    private val STARTING_PAGE_INDEX = 1

    override fun getRefreshKey(state: PagingState<Int, Category>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,Category> {
        val currentPage = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = feedRepository.favoriteRecipesRequest(currentPage)
            val favoriteItems = response

            LoadResult.Page(
                data = favoriteItems,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage.minus(
                    STARTING_PAGE_INDEX
                ),
                nextKey = if (favoriteItems.isEmpty()) null else currentPage.plus(
                    STARTING_PAGE_INDEX
                )
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
