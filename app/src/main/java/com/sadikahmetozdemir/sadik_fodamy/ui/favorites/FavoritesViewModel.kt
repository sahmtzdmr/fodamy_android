package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.core.utils.DataHelperManager
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.FavoritesCategoryModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.AuthRepository
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FeedRepository,
    private val authRepository: AuthRepository,
    private val dataHelperManager: DataHelperManager
) : BaseViewModel() {

    var recipes: MutableLiveData<PagingData<FavoritesCategoryModel>> = MutableLiveData()
    var event = MutableLiveData<String>()

    init {
        getFavoriteItems()
    }

    private fun getFavoriteItems() {
        viewModelScope.launch {
            repository.favoriteRecipesRequest().distinctUntilChanged().cachedIn(viewModelScope)
                .collectLatest { it ->
                    recipes.value = it.filter {
                        it.recipes.isNotEmpty()
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

    fun toCategories(favoritesCategoryModel: FavoritesCategoryModel) {
        navigate(
            FavoritesFragmentDirections.actionFavoritesFragmentToFavoritesCategoriesFragment(
                favoritesCategoryModel.id,
                favoritesCategoryModel.name
            )
        )
    }

    fun openDetailScreen(recipeID: Int) {
        navigate(FavoritesFragmentDirections.toRecipeDetail(recipeID))
    }
}
