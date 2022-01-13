package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.core.utils.DataHelperManager
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Status
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
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
    var recipes: MutableLiveData<PagingData<EditorChoiceModel>> = MutableLiveData()
    val recipeID: Int = savedStateHandle.get<Int>(RECIPE_ID) ?: 0
    val comment = MutableLiveData<EditorChoiceModel>()

    fun getRecipeCommentsItem() {
        viewModelScope.launch {
            feedRepository.recipeCommentsRequest(recipeID).distinctUntilChanged()
                .cachedIn(viewModelScope).collectLatest {
                    recipes.value = it
                }
        }
    }

    fun postRecipeComment(text: String) {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()) {
                navigate(RecipeCommentsFragmentDirections.toAuthDialogFragment())
            } else {
                val response = feedRepository.postRecipeCommentRequest(recipeID, text)
                when (response.status) {
                    Status.SUCCESS -> {
                        event.postValue(RecipeCommentsEvent.Success(R.string.comment_added.toString()))
                    }
                    Status.ERROR -> {
                        response.message?.let { showMessage(it) }
                    }
                    Status.LOADING -> {

                    }
                    Status.REDIRECT -> {

                    }
                }
            }
        }
    }

    fun deleteRecipeComments() {
        viewModelScope.launch {
            if (!dataHelperManager.isLogin()
            ) {
                navigate(RecipeCommentsFragmentDirections.toAuthDialogFragment())
            }

            val response =
                comment.value?.id?.let { feedRepository.deleteRecipeComment(recipeID, it) }
            when (response?.status) {
                Status.SUCCESS -> {
                    response.message?.let { showMessage(it) }
                }
                Status.ERROR -> {
                    response.message?.let { showMessage(it) }
                }
                Status.LOADING -> {
                }
                Status.REDIRECT -> {
                }
                null -> response?.message?.let { showMessage(it) }
            }
        }
    }

    fun toEdit() {
        viewModelScope.launch {
            val userID = dataHelperManager.getID()
            if (comment.value?.id == userID) {
                navigate(
                    RecipeCommentsFragmentDirections.toCommentEditFragment(
                        comment.value!!,
                        recipeID
                    )
                )

            }
        }


    }

    companion object {
        private const val RECIPE_ID: String = "recipeID"
    }
}
