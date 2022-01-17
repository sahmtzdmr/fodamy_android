package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentEditViewModel @Inject constructor(
    val feedRepository: FeedRepository,
    savedStateHandle: SavedStateHandle

) : BaseViewModel() {
    val recipeID = savedStateHandle.get<Int>(RECIPE_ID) ?: 0
    val comment = savedStateHandle.get<EditorChoiceModel>(COMMENT)
    val editableComment = MutableLiveData<String>(comment?.text)

    fun saveOnClick() {
        viewModelScope.launch {
            val response = feedRepository.editRecipeComment(
                recipeID,
                comment?.id!!,
                editableComment.value.toString()
            )
            when (response.status) {
                Status.SUCCESS -> {
                    popBackStack()
                }
                Status.ERROR -> response.data?.message?.let { showToast(it) }
                Status.LOADING -> {
                }
                Status.REDIRECT -> {
                }
            }
        }
    }

    companion object {
        val RECIPE_ID = "recipeID"
        val COMMENT = "comment"
    }
}
