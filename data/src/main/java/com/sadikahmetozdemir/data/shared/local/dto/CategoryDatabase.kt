package com.sadikahmetozdemir.data.shared.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDatabase(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: ImageDatabase,
    val recipes: List<RecipeDatabase>
)