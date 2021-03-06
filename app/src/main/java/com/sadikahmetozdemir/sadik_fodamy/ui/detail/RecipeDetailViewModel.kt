package com.sadikahmetozdemir.sadik_fodamy.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository
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
    private var recipeID: Int = savedStateHandle.get(RECIPE_ID) ?: 0

    init {
        fetchRecipeDetail()
        fetchRecipeDetailComment()
    }

    private fun fetchRecipeDetail() {
        sendRequest(
            request = { feedRepository.fetchRecipeDetail(recipeID) },
            success = {
                recipeDetail.postValue(it)
            }
        )
    }

    private fun fetchRecipeDetailComment() {
        sendRequest(
            request = { feedRepository.fetchRecipeDetailComment(recipeID) },
            success = { recipeDetailComment.postValue(it) }
        )
    }

    fun recipeLike() {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()) {
                navigate(RecipeDetailFragmentDirections.toAuthDialogFragment())
            } else {
                sendRequest(
                    request = {
                        recipeDetail.value?.id?.let {
                            feedRepository.userRecipeLikeRequest(
                                it
                            )
                        }
                    },
                    success = {
                        event.postValue(
                            it?.message?.let { it1 ->
                                RecipeDetailEvent.IsLiked(
                                    it1
                                )
                            }
                        )
                        fetchRecipeDetail()
                    }
                )
            }
        }
    }

    fun recipeDislike() {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()) {
                navigate(RecipeDetailFragmentDirections.toAuthDialogFragment())
            } else {
                sendRequest(
                    request = {
                        recipeDetail.value?.id?.let {
                            feedRepository.userRecipeDislikeRequest(
                                it
                            )
                        }
                    },
                    success = {
                        event.postValue(
                            it?.message?.let { it1 ->
                                RecipeDetailEvent.IsDisliked(
                                    it1
                                )
                            }
                        )
                        fetchRecipeDetail()
                    }
                )
            }
        }
    }

    fun userFollow(followId: Int) {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()) {
                navigate(RecipeDetailFragmentDirections.toAuthDialogFragment())
            } else {
                sendRequest(
                    request = { feedRepository.userFollowRequest(followId) },
                    success = { fetchRecipeDetail() }
                )
            }
        }
    }

    fun userUnfollow() {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()) {
                navigate(RecipeDetailFragmentDirections.toAuthDialogFragment())
            } else {
                sendRequest(
                    request = {
                        recipeDetail.value?.user?.id?.let {
                            feedRepository.userUnfollowRequest(
                                it
                            )
                        }
                    },
                    success = {
                        event.postValue(
                            it?.message.let {
                                it?.let { it1 ->
                                    RecipeDetailEvent.IsUnfollowed(
                                        it1
                                    )
                                }
                            }
                        )
                        fetchRecipeDetail()
                    }
                )
            }
        }
    }

    fun openRecipeImages() {
        navigate(RecipeDetailFragmentDirections.toRecipeImages(recipeDetail.value))
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
