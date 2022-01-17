package com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.ui.home.main.HomeTablayoutFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorChoiceViewModel @Inject constructor(private val feedRepository: FeedRepository) :
    BaseViewModel() {

    var recipes: MutableLiveData<PagingData<EditorChoiceModel>> = MutableLiveData()


    init {
        getEditorChoice()
    }

    private fun getEditorChoice() {
        viewModelScope.launch {
            feedRepository.feedRequest().distinctUntilChanged().cachedIn(viewModelScope)
                .collectLatest {
                    recipes.value = it
                }
        }
    }

    fun openDetailScreen(recipeID: Int) {
        navigate(HomeTablayoutFragmentDirections.toRecipeDetail(recipeID))
    }
}
