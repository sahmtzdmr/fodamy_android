package com.sadikahmetozdemir.data.shared.local

import com.sadikahmetozdemir.data.shared.remote.CategoryModel
import java.io.File

data class PostRecipeModel(
    val title: Int,
    val ingredients: String,
    val directions: String,
    val categoryID: CategoryModel,
    val numberOfPersonID: NumberOfPersonModel,
    val timeOfRecipeID: TimeOfRecipeModel,
    val image: File
)
