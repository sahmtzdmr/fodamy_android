package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.FavoritesCategoryModel
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesCategoriesViewModel @Inject constructor(private val repository: FeedRepository):ViewModel(){

    var recipes: MutableLiveData<PagingData<EditorChoiceModel>> = MutableLiveData()




    fun getFavoriteCategoriesItem(categoryID: Int){
        viewModelScope.launch {
            repository.favoriteCategoriesRequest(categoryID).distinctUntilChanged().cachedIn(viewModelScope).collectLatest {
                recipes.value=it
            }
        }

    }
}