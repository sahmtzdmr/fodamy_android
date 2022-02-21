package com.sadikahmetozdemir.data.shared.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDatabase(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    val username: String?,
    val favoritesCount: Int?,
    val followedCount: Int?,
    val followingCount: Int?,
    val isFollowing: Boolean,
    val likesCount: Int?,
    val recipeCount: Int?,
    val image: ImageDatabase?
)