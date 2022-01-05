package com.sadikahmetozdemir.sadik_fodamy.ui.comment

sealed class RecipeCommentsEvent {
    data class Success(val message: String) : RecipeCommentsEvent()
}
