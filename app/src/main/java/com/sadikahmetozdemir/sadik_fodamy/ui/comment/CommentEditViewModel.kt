package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.domain.requests.Status
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentEditViewModel @Inject constructor(
    val feedRepository: FeedRepository,
    savedStateHandle: SavedStateHandle

) : BaseViewModel() {
    val recipeID = savedStateHandle.get<Int>(RECIPE_ID) ?: 0
    val comment = savedStateHandle.get<Comment>(COMMENT)
    val editableComment = MutableLiveData<String>(comment?.text)

    fun saveOnClick() {
        viewModelScope.launch {
            val response = feedRepository.editRecipeComment(
                recipeID,
                comment?.id!!,
                editableComment.value.toString()
            )
            sendRequest(success = popBackStack(),
            error = response.message?.let { showToast(it) })
        }
    }

    companion object {
        val RECIPE_ID = "recipeID"
        val COMMENT = "comment"
    }
}
