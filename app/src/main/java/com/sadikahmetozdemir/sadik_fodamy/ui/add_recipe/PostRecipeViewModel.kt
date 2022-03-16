package com.sadikahmetozdemir.sadik_fodamy.ui.add_recipe

import androidx.lifecycle.MutableLiveData
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.NumberOfPerson
import com.sadikahmetozdemir.domain.entities.TimeOfRecipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import javax.inject.Inject

class PostRecipeViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
    private val dataHelperManager: DataHelperManager
) : BaseViewModel() {
    var timeOfRecipes = MutableLiveData<List<TimeOfRecipe>>()
    val numberOfRecipes = MutableLiveData<List<NumberOfPerson>>()
    val title = MutableLiveData("")
    val ingredients = MutableLiveData("")
    val directions = MutableLiveData("")

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


}