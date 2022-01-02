package com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorChoiceViewModel@Inject constructor(private val feedRepository: FeedRepository) : BaseViewModel() {

    var _recipes: MutableLiveData<PagingData<EditorChoiceModel>> = MutableLiveData()
    val recipes: LiveData<PagingData<EditorChoiceModel>> get() = _recipes

    init {
        getEditorChoice()
    }

    fun getEditorChoice() {
        viewModelScope.launch {
            feedRepository.feedRequest().distinctUntilChanged().cachedIn(viewModelScope).collectLatest {
                _recipes.value = it
            }
        }
    }
}
