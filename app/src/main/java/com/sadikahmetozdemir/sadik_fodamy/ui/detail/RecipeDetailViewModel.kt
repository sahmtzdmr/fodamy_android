package com.sadikahmetozdemir.sadik_fodamy.ui.detail

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.CommentResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val repository: FeedRepository,
    private val sharedPreferences: SharedPreferences,
    private val savedStateHandle: SavedStateHandle,

) : BaseViewModel() {
    val recipeDetail = MutableLiveData<EditorChoiceModel?>()
    val recipeDetailComment = MutableLiveData<CommentResponseModel?>()
    var showErrorMessage = MutableLiveData<String?>()
    val event = MutableLiveData<RecipeDetailEvent>()
    var recipeID: Int = savedStateHandle.get("recipeId") ?: 0

    fun getRecipeDetail(
        recipeID: Int
    ) {
        viewModelScope.launch {
            val response = repository.getRecipeDetail(recipeID)
            when (response?.status) {
                Status.SUCCESS -> {
                    recipeDetail.postValue(response.data)

                    //    println(recipeDetail.value)
                }
                Status.ERROR -> {
                    //   showErrorMessage.postValue(response.message.toString())
                }
            }
        }
    }

    fun getRecipeDetailComment(
        recipeID: Int
    ) {
        viewModelScope.launch {
            val response = repository.getRecipeDetailComment(recipeID)
            when (response?.status) {
                Status.SUCCESS -> {
                    recipeDetailComment.postValue(response.data)
                }
                Status.ERROR -> {

                    //      showErrorMessage.postValue(response.message.toString())
                }
            }
        }
    }

    fun recipeLike(recipeID: Int) {
        viewModelScope.launch {
            println(sharedPreferences.getString(SharedPreferanceStorage.PREFS_USER_TOKEN, "sdasda"))
            if (sharedPreferences.getString(SharedPreferanceStorage.PREFS_USER_TOKEN, "")
                .isNullOrBlank()
            ) {
                event.postValue(RecipeDetailEvent.OpenDialog(RecipeDetailFragmentDirections.toAuthDialogFragment()))
            }

            val response = repository.userRecipeLikeRequest(recipeID)
            when (response?.status) {
                Status.SUCCESS -> {

                    event.postValue(
                        response.data?.message?.let {
                            RecipeDetailEvent.IsLiked(it)
                        }
                    )
                    getRecipeDetail(recipeID)
                }
                Status.ERROR -> {
                    response.data?.message?.let { showMessage(it) }
                }
            }
        }
    }

    fun recipeDislike(recipeID: Int) {
        viewModelScope.launch {
            println(sharedPreferences.getString(SharedPreferanceStorage.PREFS_USER_TOKEN, "sdasda"))
            if (sharedPreferences.getString(SharedPreferanceStorage.PREFS_USER_TOKEN, "")
                .isNullOrBlank()
            ) {
                event.postValue(RecipeDetailEvent.OpenDialog(RecipeDetailFragmentDirections.toAuthDialogFragment()))
            }

            val response = repository.userRecipeDislikeRequest(recipeID)
            when (response?.status) {
                Status.SUCCESS -> {

                    event.postValue(
                        response.data?.message?.let {

                            RecipeDetailEvent.IsDisliked(it)
                        }
                    )

                    getRecipeDetail(recipeID)
                }
                Status.ERROR -> {
                    Log.d(TAG, "recipeLike: sadasd")
                }
            }
        }
    }

    fun userFollow(followId: Int) {
        viewModelScope.launch {
            if (sharedPreferences.getString(SharedPreferanceStorage.PREFS_USER_TOKEN, "")
                .isNullOrBlank()
            ) {
                event.postValue(RecipeDetailEvent.OpenDialog(RecipeDetailFragmentDirections.toAuthDialogFragment()))
            } else {
                val response = repository.userFollowRequest(followId)
                when (response.status) {
                    Status.SUCCESS -> {
                        event.postValue(
                            response.data?.message.let {
                                it?.let { it1 ->
                                    RecipeDetailEvent.IsFollowed(
                                        it1
                                    )
                                }
                            }
                        )
                        getRecipeDetail(recipeID)
                    }
                    Status.ERROR -> {
                        Log.d(TAG, "recipeLike: sadasd")
                    }
                }
            }
        }
    }

    fun userUnfollow(followId: Int) {
        viewModelScope.launch {
            if (sharedPreferences.getString(SharedPreferanceStorage.PREFS_USER_TOKEN, "")
                .isNullOrBlank()
            ) {
                event.postValue(RecipeDetailEvent.OpenDialog(RecipeDetailFragmentDirections.toAuthDialogFragment()))
            } else {
                val response = repository.userUnfollowRequest(followId)
                when (response.status) {
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
                        getRecipeDetail(recipeID)
                    }
                    Status.ERROR -> {
                        Log.d(TAG, "recipeLike: sadasd")
                    }
                }
            }
        }
    }
    fun openRecipeImages(recipeID: EditorChoiceModel) {
        navigate(RecipeDetailFragmentDirections.toRecipeImages(recipeID))
    }
    fun toCommentsScreen() {
        println("sadık")
        navigate(RecipeDetailFragmentDirections.toRecipeComments(recipeID))
    }

    companion object {
        const val TAG = "RecipeDetailViewModel"
    }
    fun bottomSheetUnfollow() {
        navigate(RecipeDetailFragmentDirections.toBottomSheet())
    }
}
