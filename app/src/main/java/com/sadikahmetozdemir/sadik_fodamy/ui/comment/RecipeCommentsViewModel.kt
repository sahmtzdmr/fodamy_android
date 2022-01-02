package com.sadikahmetozdemir.sadik_fodamy.ui.comment

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
class RecipeCommentsViewModel @Inject constructor(private val repository: FeedRepository) :
    BaseViewModel() {
    var recipes: MutableLiveData<PagingData<EditorChoiceModel>> = MutableLiveData()

    fun getRecipeCommentsItem(recipeID: Int) {
        viewModelScope.launch {
            repository.recipeCommentsRequest(recipeID).distinctUntilChanged().cachedIn(viewModelScope).collectLatest {
                recipes.value = it
            }
        }
    }
}
