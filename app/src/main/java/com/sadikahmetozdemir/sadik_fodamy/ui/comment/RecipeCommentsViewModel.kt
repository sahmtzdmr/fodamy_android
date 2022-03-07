package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeCommentsViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
    private val dataHelperManager: DataHelperManager,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel() {
    val event = SingleLiveEvent<RecipeCommentsEvent>()
    var recipes: MutableLiveData<PagingData<Comment>> = MutableLiveData()
    val recipeID: Int = savedStateHandle.get<Int>(RECIPE_ID) ?: 0
    val comment: MutableLiveData<Comment> = MutableLiveData<Comment>()

    fun getRecipeCommentsItem() {
        sendRequest(
            request = {
                feedRepository.getRecipeCommentFromMediator(recipeID)
            }, success = {
                viewModelScope.launch {
                    it.cachedIn(viewModelScope).collect { recipes.value = it }
                }
            }
        )
    }

    fun postRecipeComment(text: String) {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()) {
                navigate(RecipeCommentsFragmentDirections.toAuthDialogFragment())
            } else {
                sendRequest(
                    request = {
                        feedRepository.postRecipeCommentRequest(recipeID, text)
                    },
                    success = {
                        event.postValue(RecipeCommentsEvent.Success(R.string.comment_added.toString()))
                    }
                )
            }
        }
    }

    fun deleteRecipeComments() {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()
            ) {
                navigate(RecipeCommentsFragmentDirections.toAuthDialogFragment())
            }
            sendRequest(request = {
                comment.value?.id?.let {
                    feedRepository.deleteRecipeComment(
                        recipeID,
                        it
                    )
                }
            }, success = { it?.message?.let { it1 -> showMessage(it1) } })
        }
    }

    fun toDialog() {
        navigate(
            RecipeCommentsFragmentDirections.tocommentDialogFragment(
                comment.value!!,
                recipeID
            )
        )
    }

    fun toUserProfile(userID: Int) {
        navigate(RecipeCommentsFragmentDirections.toUserProfile(userID))
    }

    companion object {
        private const val RECIPE_ID: String = "recipeID"
        private val PAGE_CONFIG =
            PagingConfig(maxSize = 100, pageSize = 24, enablePlaceholders = false)
    }
}
