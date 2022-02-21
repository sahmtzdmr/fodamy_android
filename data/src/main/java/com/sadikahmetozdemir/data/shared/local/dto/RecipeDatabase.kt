package com.sadikahmetozdemir.data.shared.local.dto

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeDatabase(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String?,
    val definition: String,
    val ingredients: String,
    val directions: String,
    val difference: String,
    @ColumnInfo(name = "is_editor_choice")
    val isEditorChoice: Boolean,
    @ColumnInfo(name = "is_liked")
    val haveLiked: Boolean,
    @ColumnInfo(name = "like_count")
    val likeCount: Int,
    @ColumnInfo(name = "comment_count")
    val commentCount: Int,
    val user: UserDatabase?,
    @Embedded(prefix = "time_of_recipe")
    val timeOfRecipe: TimeOfRecipeDatabase?,
    @Embedded(prefix = "number_of_person")
    val numberOfPerson: NumberOfPersonDatabase?,
    @ColumnInfo(name = "is_last_added")
    val isLastAdded: Boolean = false,
    @ColumnInfo(name = "category_id")
    val category: CategoryDatabase?,
    val image: List<ImageDatabase>?

)