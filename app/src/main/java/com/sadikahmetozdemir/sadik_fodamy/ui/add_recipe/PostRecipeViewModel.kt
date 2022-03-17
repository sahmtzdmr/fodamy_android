package com.sadikahmetozdemir.sadik_fodamy.ui.add_recipe

import androidx.lifecycle.MutableLiveData
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.entities.NumberOfPerson
import com.sadikahmetozdemir.domain.entities.TimeOfRecipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostRecipeViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
    private val dataHelperManager: DataHelperManager
) : BaseViewModel() {
    var timeOfRecipes = MutableLiveData<List<TimeOfRecipe>>()
    val numberOfRecipes = MutableLiveData<List<NumberOfPerson>>()
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
}
