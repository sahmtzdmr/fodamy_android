package com.sadikahmetozdemir.sadik_fodamy.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.domain.requests.Status
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
    private val dataHelperManager: DataHelperManager,
    savedStateHandle: SavedStateHandle,

    ) : BaseViewModel() {
    val recipeDetail = MutableLiveData<Recipe?>()
    val recipeDetailComment = MutableLiveData<Comment?>()
    val event = MutableLiveData<RecipeDetailEvent>()
    var recipeID: Int = savedStateHandle.get(RECIPE_ID) ?: 0

    init {
        getRecipeDetail()
        getRecipeDetailComment()
    }

    private fun getRecipeDetail() {
        viewModelScope.launch {
            val response = feedRepository.getRecipeDetail(recipeID)

            recipeDetail.postValue(response)


        }
    }


    private fun getRecipeDetailComment() {
        viewModelScope.launch {
            val response = feedRepository.getRecipeDetailComment(recipeID)

            recipeDetailComment.postValue(response)

        }
    }


    fun recipeLike(recipeID: Int) {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()) {
                navigate(RecipeDetailFragmentDirections.toAuthDialogFragment())
            }

            val response = feedRepository.userRecipeLikeRequest(recipeID)
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

            val response = feedRepository.userRecipeDislikeRequest(recipeID)
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
                val response = feedRepository.userFollowRequest(followId)
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
                    recipeDetail.value?.user?.id?.let { feedRepository.userUnfollowRequest(it) }
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

    fun openRecipeImages(recipeID: Recipe) {
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
