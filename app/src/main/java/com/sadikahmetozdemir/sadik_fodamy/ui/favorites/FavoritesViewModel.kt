package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.sadikahmetozdemir.data.shared.repositories.FavoritesPagingSource
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
    private val authRepository: AuthRepository,
    private val dataHelperManager: DataHelperManager
) : BaseViewModel() {

    var recipes: MutableLiveData<PagingData<Category>> = MutableLiveData()
    var event = MutableLiveData<String>()

    init {
        getFavoriteItems()
    }

    private fun getFavoriteItems() {
        sendRequest(request = {
            Pager(config = PAGE_CONFIG, pagingSourceFactory = {
                FavoritesPagingSource(feedRepository)
            }).flow
        },
            success = {
                viewModelScope.launch {
                    it.cachedIn(viewModelScope).collect {
                        recipes.value = it.filter {
                            it.recipes?.isNotEmpty() == true
                        }
                    }
                }
            }
        )
    }

    fun logoutRequest() {
        sendRequest(request = {
            authRepository.logoutRequest()
        }, success = {
            viewModelScope.launch { dataHelperManager.removeToken() }
            it.message?.let { it1 -> showToast(it1) }
        })
    }

    fun toCategories(category: Category) {
        navigate(
            FavoritesFragmentDirections.actionFavoritesFragmentToFavoritesCategoriesFragment(
                category.id!!,
                category.name!!
            )
        )
    }

    fun openDetailScreen(recipeID: Int) {
        navigate(FavoritesFragmentDirections.toRecipeDetail(recipeID))
    }

    companion object {
        private val PAGE_CONFIG =
            PagingConfig(maxSize = 100, pageSize = 24, enablePlaceholders = false)

    }
}
