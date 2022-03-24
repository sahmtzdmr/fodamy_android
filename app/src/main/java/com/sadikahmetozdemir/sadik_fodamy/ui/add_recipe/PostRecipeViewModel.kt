@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.sadikahmetozdemir.sadik_fodamy.ui.add_recipe

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.entities.NumberOfPerson
import com.sadikahmetozdemir.domain.entities.TimeOfRecipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PostRecipeViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
) : BaseViewModel() {
    var timeOfRecipes = MutableLiveData<List<TimeOfRecipe>>()
    val numberOfRecipes = MutableLiveData<List<NumberOfPerson>>()
    var timeOfRecipeNumber: Int? = null
    var numberOfPersonID: Int? = null
    var image: Uri? = null
    var categoryID: Int? = null
    val category = MutableLiveData<List<Category>>()
    val title = MutableLiveData("")
    val ingredients = MutableLiveData("")
    val directions = MutableLiveData("")

    init {
        getRecipeTime()
        getRecipeNumber()
        getCategoryId()
    }

    private fun getCategoryId() {
        sendRequest(
            request = { feedRepository.getRecipeCategory() },
            success = {
                category.value = it
            }
        )
    }

    private fun getRecipeTime() {
        sendRequest(
            request = { feedRepository.getRecipeTime() },
            success = {
                timeOfRecipes.value = it
            }
        )
    }

    private fun getRecipeNumber() {
        sendRequest(
            request = { feedRepository.getRecipeServing() },
            success = {
                numberOfRecipes.value = it
            }
        )
    }

    fun postNewRecipe() {
        sendRequest(request = {
            feedRepository.postNewRecipeRequest(
                title = title.value.toString(),
                ingredients = ingredients.value.toString(),
                direction = directions.value.toString(),
                categoryID = categoryID ?: -1,
                numberOfPersonID = numberOfPersonID ?: -1,
                timeOfRecipeID = timeOfRecipeNumber ?: -1,
                image = File(),

                )
        }, success = {
            it
        })
    }
}
