package com.sadikahmetozdemir.sadik_fodamy.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.core.utils.DataHelperManager
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.CommentResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val repository: FeedRepository,
    private val dataHelperManager: DataHelperManager,
    savedStateHandle: SavedStateHandle,

    ) : BaseViewModel() {
    val recipeDetail = MutableLiveData<EditorChoiceModel?>()
    val recipeDetailComment = MutableLiveData<CommentResponseModel?>()
    val event = MutableLiveData<RecipeDetailEvent>()
    var recipeID: Int = savedStateHandle.get(RECIPE_ID) ?: 0

    init {
        getRecipeDetail()
        getRecipeDetailComment()
    }

    private fun getRecipeDetail() {
        viewModelScope.launch {
            val response = repository.getRecipeDetail(recipeID)
            when (response.status) {
                Status.SUCCESS -> {
                    recipeDetail.postValue(response.data)
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {
                }
                Status.REDIRECT -> {
                }
            }
        }
    }

    private fun getRecipeDetailComment() {
        viewModelScope.launch {
            val response = repository.getRecipeDetailComment(recipeID)
            when (response.status) {
                Status.SUCCESS -> {
                    recipeDetailComment.postValue(response.data)
                }
                Status.ERROR -> {

                    response.message?.let { showToast(it) }
                }
                Status.LOADING -> {
                }
                Status.REDIRECT -> {
                }
            }
        }
    }

    fun recipeLike(recipeID: Int) {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()) {
                navigate(RecipeDetailFragmentDirections.toAuthDialogFragment())
            }

            val response = repository.userRecipeLikeRequest(recipeID)
            when (response.status) {
                Status.SUCCESS -> {

                    event.postValue(
                        response.data?.message?.let {
                            RecipeDetailEvent.IsLiked(it)
                        }
                    )
                    getRecipeDetail()
                }
                Status.ERROR -> {
                    response.data?.message?.let { showMessage(it) }
                }
                Status.LOADING -> {
                }
                Status.REDIRECT -> {
                }
            }
        }
    }

    fun recipeDislike(recipeID: Int) {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()) {
                navigate(RecipeDetailFragmentDirections.toAuthDialogFragment())

            }

            val response = repository.userRecipeDislikeRequest(recipeID)
            when (response.status) {
                Status.SUCCESS -> {

                    event.postValue(
                        response.data?.message?.let {

                            RecipeDetailEvent.IsDisliked(it)
                        }
                    )

                    getRecipeDetail()
                }
                Status.ERROR -> {
                    response.message?.let { showToast(it) }
                }
                Status.LOADING -> {
                }
                Status.REDIRECT -> {
                }
            }
        }
    }

    fun userFollow(followId: Int) {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()) {
                navigate(RecipeDetailFragmentDirections.toAuthDialogFragment())
            } else {
                val response = repository.userFollowRequest(followId)
                when (response.status) {
                    Status.SUCCESS -> {
                        getRecipeDetail()
                    }
                    Status.ERROR -> {
                        response.data?.message?.let { showToast(it) }
                    }
                    Status.LOADING -> {
                    }
                    Status.REDIRECT -> {
                    }
                }
            }
        }
    }

    fun userUnfollow() {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()) {
                navigate(RecipeDetailFragmentDirections.toAuthDialogFragment())
            } else {
                val response =
                    recipeDetail.value?.user?.id?.let { repository.userUnfollowRequest(it) }
                when (response?.status) {
                    Status.SUCCESS -> {

                        event.postValue(
                            response.data?.message.let {
                                it?.let { it1 ->
                                    RecipeDetailEvent.IsUnfollowed(
                                        it1
                                    )
                                }
                            }
                        )
                        getRecipeDetail()
                    }
                    Status.ERROR -> {
                        response.data?.message?.let { showToast(it) }
                    }
                    Status.LOADING -> {
                    }
                    Status.REDIRECT -> {
                    }
                    null -> {
                    }
                }
            }
        }
    }

    fun openRecipeImages(recipeID: EditorChoiceModel) {
        navigate(RecipeDetailFragmentDirections.toRecipeImages(recipeID))
    }

    fun toCommentsScreen() {
        navigate(RecipeDetailFragmentDirections.toRecipeComments(recipeID))
    }

    companion object {
        const val RECIPE_ID = "recipeId"
    }

    fun bottomSheetUnfollow() {
        navigate(RecipeDetailFragmentDirections.toBottomSheet())
    }
}
