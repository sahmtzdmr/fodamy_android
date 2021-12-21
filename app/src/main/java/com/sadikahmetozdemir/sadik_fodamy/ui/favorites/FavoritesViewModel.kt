package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.FavoritesCategoryModel
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: FeedRepository):ViewModel(){

    var recipes:MutableLiveData<PagingData<FavoritesCategoryModel>> =MutableLiveData()

init {
    getFavoriteItems()
}


    fun getFavoriteItems(){
        viewModelScope.launch {
            repository.favoriteRecipesRequest().distinctUntilChanged().cachedIn(viewModelScope).collectLatest {

            recipes.value=it.filter {
                it.recipes.size>0
            }
            }
        }

    }
}