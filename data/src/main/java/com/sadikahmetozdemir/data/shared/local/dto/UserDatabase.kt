package com.sadikahmetozdemir.data.shared.local.dto

data class UserDatabase(
    val id: Int,
    val name: String,
    val username: String,
    val favoritesCount: Int,
    val followedCount: Int,
    val followingCount: Int,
    val isFollowing: Boolean,
    val likesCount: Int,
    val recipeCount: Int,
    val image: ImageDatabase
)