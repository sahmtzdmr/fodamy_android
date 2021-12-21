package com.sadikahmetozdemir.sadik_fodamy.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.*
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val retrofit: Retrofit,
    private val repository: FeedRepository
) : ViewModel() {
    val recipeDetail = MutableLiveData<EditorChoiceModel?>()
    val recipeDetailComment=MutableLiveData<CommentResponseModel?>()
    var showErrorMessage=MutableLiveData<String?>()


     fun getRecipeDetail(
        recipeID: Int
    ) {
        viewModelScope.launch {
            val response = repository.getRecipeDetail(recipeID)
            when (response?.status) {
                Status.SUCCESS -> {
                    recipeDetail.postValue(response.data)

                    println(recipeDetail.value)


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

}