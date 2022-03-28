package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class PostRecipe(
    val title: Int,
    val ingredients: String,
    val directions: String,
    val categoryID: Category,
    val numberOfPersonID: NumberOfPerson,
    val timeOfRecipeID: TimeOfRecipe,
    val image: File
) : Parcelable
