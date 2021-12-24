package com.sadikahmetozdemir.sadik_fodamy.ui

sealed class RecipeDetailEvent (){
    data class IsLiked( val message: String): RecipeDetailEvent()



}