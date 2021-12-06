package com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.sadik_fodamy.api.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiseResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

@HiltViewModel
class EditorChoiceViewModel@Inject constructor(private val feedRepository: FeedRepository) :ViewModel(){

    var _recipes: MutableLiveData<PagingData<EditorChoiceModel>> = MutableLiveData()
    val recipes: LiveData<PagingData<EditorChoiceModel>> get() = _recipes


init {
    getEditorChoice()
}

    fun getEditorChoice(){
        viewModelScope.launch {
           feedRepository.feedRequest().distinctUntilChanged().collectLatest{
               _recipes.value=it

           }

       }


    }

}