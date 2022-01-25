package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.data.shared.repositories.FavoriteCategoriesPagingSource
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewEvent
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.domain.requests.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesCategoriesViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
    private val authRepository: AuthRepository,
    private val dataHelperManager: DataHelperManager,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val title = savedStateHandle.get<String>(TITLE)
    var recipes: MutableLiveData<PagingData<Recipe>> = MutableLiveData()
    var event = MutableLiveData<BaseViewEvent>()

    fun getFavoriteCategoriesItem(categoryID: Int) {
        viewModelScope.launch {
            val pager = Pager(
                config = PAGE_CONFIG,
                pagingSourceFactory = {
                    FavoriteCategoriesPagingSource(
                        feedRepository,
                        categoryID
                    )
                }).flow
            pager.cachedIn(viewModelScope).collect { recipes.value = it }
        }
    }

    fun logoutRequest() {
        viewModelScope.launch {
            val response = authRepository.logoutRequest()
            when (response.status) {
                Status.SUCCESS -> {
                    dataHelperManager.removeToken()
                    response.data?.message?.let { showToast(it) }
                }

                Status.ERROR -> {
                    response.data?.message?.let { showToast(it) }
                }
                Status.LOADING -> {
                }
                Status.REDIRECT -> {
                }
            }
        }
    }

    fun toRecipeDetail(recipe: Recipe) {
        recipe.id?.let { FavoritesCategoriesFragmentDirections.toRecipeDetail(it) }
            ?.let { navigate(it) }
    }

    companion object {
        private const val TITLE = "title"
        private val PAGE_CONFIG =
            PagingConfig(maxSize = 100, pageSize = 24, enablePlaceholders = false)
    }
}
