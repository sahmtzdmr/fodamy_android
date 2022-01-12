package com.sadikahmetozdemir.sadik_fodamy.ui.detail

sealed class RecipeDetailEvent {
    data class IsLiked(val message: String) : RecipeDetailEvent()
    data class IsDisliked(val message: String) : RecipeDetailEvent()
    data class IsUnfollowed(val message: String) : RecipeDetailEvent()
}
