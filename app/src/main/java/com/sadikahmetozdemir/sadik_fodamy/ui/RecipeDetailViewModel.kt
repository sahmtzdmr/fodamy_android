package com.sadikahmetozdemir.sadik_fodamy.ui

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.*
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.AuthRepository
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val retrofit: Retrofit,
    private val authRepository: AuthRepository,
    private val repository: FeedRepository,
    private val sharedPreferences: SharedPreferences,
    private val savedStateHandle: SavedStateHandle,

    ) : ViewModel() {
    val recipeDetail = MutableLiveData<EditorChoiceModel?>()
    val recipeDetailComment = MutableLiveData<CommentResponseModel?>()
    var showErrorMessage = MutableLiveData<String?>()
    val recipeLiked = MutableLiveData<RecipeDetailEvent>()
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
                    Log.d(TAG, "recipeLike: sadasd")
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
                    }
                    Status.ERROR -> {
                        Log.d(TAG, "recipeLike: sadasd")
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "RecipeDetailViewModel"
    }
}
