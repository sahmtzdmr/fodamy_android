package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: Int?,
    val title: String?,
    val definition: String?,
    val ingredients: String?,
    val directions: String?,
    val difference: String?,
    val isEditorChoice: Boolean?,
    val isLiked: Boolean?,
    val likeCount: Int?,
    val numberOfFavoriteCount: String?,
    val commentCount: Int?,
    val user: User?,
    val timeOfRecipe: TimeOfRecipe?,
    val numberOfPerson: NumberOfPerson?,
    val category: Category?,
    val images: List<Images>?,
    var categoryModel: Category?
) : Parcelable
