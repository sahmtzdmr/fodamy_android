package com.sadikahmetozdemir.data.shared.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.entities.Images
import com.sadikahmetozdemir.domain.entities.NumberOfPerson
import com.sadikahmetozdemir.domain.entities.TimeOfRecipe
import com.sadikahmetozdemir.domain.entities.User

@Entity(tableName = "recipes")
data class RecipeDataBase(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String?,
    val definition: String?,
    val ingredients: String?,
    val directions: String?,
    val difference: String?,
    val isEditorChoice: Boolean?,
    val haveLiked: Boolean?,
    val likeCount: Int?,
    val numberOfFavoriteCount: String?,
    val commentCount: Int?,
    val user: User?,
    val timeOfRecipe: TimeOfRecipe?,
    val numberOfPerson: NumberOfPerson?,
    val category: Category?,
    val images: List<Images>?,
    var categoryModel: Category?
)