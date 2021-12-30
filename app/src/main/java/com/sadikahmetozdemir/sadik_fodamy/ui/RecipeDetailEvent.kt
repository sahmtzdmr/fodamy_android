package com.sadikahmetozdemir.sadik_fodamy.ui

import androidx.navigation.NavDirections

sealed class RecipeDetailEvent() {
    data class IsLiked(val message: String) : RecipeDetailEvent()
    data class OpenDialog(val direction: NavDirections) : RecipeDetailEvent()
    data class ShowMessage(val message: String) : RecipeDetailEvent()
    data class IsDisliked(val message: String) : RecipeDetailEvent()
    data class IsFollowed(val message: String) : RecipeDetailEvent()
    data class IsUnfollowed(val message: String) : RecipeDetailEvent()
}
