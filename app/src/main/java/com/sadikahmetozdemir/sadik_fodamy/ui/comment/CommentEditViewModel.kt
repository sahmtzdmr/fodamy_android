package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
        sendRequest(request = {
            feedRepository.editRecipeComment(
                recipeID,
                comment?.id!!,
                editableComment.value.toString()
            )
        }, success = {
            backTo()
        })
    }

    companion object {
        val RECIPE_ID = "recipeID"
        val COMMENT = "comment"
    }
}
