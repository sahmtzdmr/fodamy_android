package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.domain.requests.Status
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
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
        viewModelScope.launch {
            feedRepository.favoriteRecipesRequest().distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collectLatest { it ->
                    recipes.value = it.filter {
                        it.recipes?.isNotEmpty() == true
                    }
                }
        }
    }

    fun logoutRequest() {
        viewModelScope.launch {
            val response = authRepository.logoutRequest()
            when (response.status) {
                Status.SUCCESS -> {
                    dataHelperManager.removeToken()
                    event.postValue(response.data?.message)
                    response.data?.message?.let { showToast(it) }
                }

                Status.ERROR -> {

                    event.postValue(response.data?.message)
                }
                Status.LOADING -> {
                }
                Status.REDIRECT -> {
                }
            }
        }
    }

    fun toCategories(category: Category) {
        navigate(
            FavoritesFragmentDirections.actionFavoritesFragmentToFavoritesCategoriesFragment(
                category.id,
                category.name
            )
        )
    }

    fun openDetailScreen(recipeID: Int) {
        navigate(FavoritesFragmentDirections.toRecipeDetail(recipeID))
    }
}
