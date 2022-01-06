package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentEditViewModel @Inject constructor(
    val repository: FeedRepository,
    val savedStateHandle: SavedStateHandle

) : BaseViewModel() {

    val recipeID = savedStateHandle.get<Int>("recipeID") ?: 0
    val comment = savedStateHandle.get<EditorChoiceModel>("comment")
    val editableComment = MutableLiveData<String>(comment?.text)

    fun saveOnClick() {
        viewModelScope.launch {
            val response = repository.editRecipeComment(
                recipeID,
                comment?.id!!,
                editableComment.value.toString()
            )
            when (response.status) {
                Status.SUCCESS -> { popBackStack() }
            }
        }
    }
}
