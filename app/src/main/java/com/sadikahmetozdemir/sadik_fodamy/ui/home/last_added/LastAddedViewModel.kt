package com.sadikahmetozdemir.sadik_fodamy.ui.home.last_added

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LastAddedViewModel @Inject constructor(private val feedRepository: FeedRepository) :
    ViewModel() {

    var recipes: MutableLiveData<PagingData<EditorChoiceModel>> = MutableLiveData()

    init {
        getLastAdded()
    }

    fun getLastAdded(){
        viewModelScope.launch {
            feedRepository.lastAddedRequest().distinctUntilChanged().cachedIn(viewModelScope).collectLatest {
                recipes.value=it
            }
        }


    }

}