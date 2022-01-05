package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import com.sadikahmetozdemir.sadik_fodamy.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeCommentsViewModel @Inject constructor(
    private val repository: FeedRepository,
    private val sharedPreferences: SharedPreferences,
    private val savedStateHandle: SavedStateHandle
) :
    BaseViewModel() {
    val event = SingleLiveEvent<RecipeCommentsEvent>()
    var recipes: MutableLiveData<PagingData<EditorChoiceModel>> = MutableLiveData()
    val recipeID: Int = savedStateHandle.get<Int>(RECIPE_ID) ?: 0
    val comment = MutableLiveData<EditorChoiceModel>()

    fun getRecipeCommentsItem() {
        viewModelScope.launch {
            repository.recipeCommentsRequest(recipeID).distinctUntilChanged()
                .cachedIn(viewModelScope).collectLatest {
                    recipes.value = it
                }
        }
    }

    fun postRecipeComment(text: String) {
        viewModelScope.launch {
            if (sharedPreferences.getString(SharedPreferanceStorage.PREFS_USER_TOKEN, "")
                .isNullOrBlank()
            ) {
                navigate(RecipeCommentsFragmentDirections.toAuthDialogFragment())
            } else {
                val response = repository.postRecipeCommentRequest(recipeID, text)
                when (response?.status) {
                    Status.SUCCESS -> {
                        event.postValue(RecipeCommentsEvent.Success("Yorum Eklendi"))
                    }
                    Status.ERROR -> {
                        response.message?.let { showMessage(it) }
                    }
                }
            }
        }
    }

    fun deleteRecipeComments() {
        viewModelScope.launch {
            println(sharedPreferences.getString(SharedPreferanceStorage.PREFS_USER_TOKEN, "sdasda"))
            if (sharedPreferences.getString(SharedPreferanceStorage.PREFS_USER_TOKEN, "")
                .isNullOrBlank()
            ) {
                navigate(RecipeCommentsFragmentDirections.toAuthDialogFragment())
            }

            val response = comment.value?.id?.let { repository.deleteRecipeComment(recipeID, it) }
            when (response?.status) {
                Status.SUCCESS -> {
                    response.message?.let { showMessage(it) }
                }
                Status.ERROR -> {
                    response.message?.let { showMessage(it) }
                }
            }
        }
    }
    fun toEdit() {
        navigate(RecipeCommentsFragmentDirections.toCommentEditFragment(comment.value!!, recipeID))
    }

    companion object {
        private const val RECIPE_ID: String = "recipeID"
    }
}
