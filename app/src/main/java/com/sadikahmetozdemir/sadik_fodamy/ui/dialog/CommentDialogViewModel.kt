package com.sadikahmetozdemir.sadik_fodamy.ui.dialog

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentDialogViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dataHelperManager: DataHelperManager
) : BaseViewModel() {
    private val comment = savedStateHandle.get<Comment>(COMMENT)
    private val recipeId = savedStateHandle.get<Int>(RECIPE_ID) ?: 1

    fun toEdit() {
        viewModelScope.launch {
            val userID = dataHelperManager.getID()
            val commentID = comment?.user?.id
            commentID
            if (comment?.user?.id == userID) {
                navigate(
                    CommentDialogFragmentDirections.toCommentEditFragment(
                        comment,
                        recipeId
                    )
                )
            }
        }
    }
    fun onCancel() {
        backTo()
    }

    companion object {
        private const val COMMENT = "comment"
        private const val RECIPE_ID = "recipeID"
    }
}
