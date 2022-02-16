package com.sadikahmetozdemir.data.shared.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
class CommentDatabase(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val text: String,
    val user: UserDatabase,
    val difference: String,
    @ColumnInfo(name="recipe_id")
    val recipeId:Int
)