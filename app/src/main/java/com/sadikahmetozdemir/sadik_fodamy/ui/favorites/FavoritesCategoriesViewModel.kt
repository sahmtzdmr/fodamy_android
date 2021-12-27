package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.FavoritesCategoryModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.AuthRepository
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesCategoriesViewModel @Inject constructor(
    private val repository: FeedRepository,
    private val authRepository: AuthRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    var recipes: MutableLiveData<PagingData<EditorChoiceModel>> = MutableLiveData()
    var event= MutableLiveData<FavoritesEvent>()


    fun getFavoriteCategoriesItem(categoryID: Int) {
        viewModelScope.launch {
            repository.favoriteCategoriesRequest(categoryID).distinctUntilChanged()
                .cachedIn(viewModelScope).collectLatest {
                recipes.value = it
            }
        }

    }

    fun logoutRequest() {
        viewModelScope.launch {
            val response = authRepository.logoutRequest()
            when (response.status){
                Status.SUCCESS ->{
                    sharedPreferences.edit().remove(SharedPreferanceStorage.PREFS_USER_TOKEN).apply()
                    event.postValue(response.data?.message?.let { FavoritesEvent.ShowMessage(it) })


                }

                Status.ERROR -> TODO()
                Status.LOADING -> TODO()
                Status.REDIRECT -> TODO()
            }
        }

    }
}
