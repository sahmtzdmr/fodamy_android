package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int?,
    val image: Images?,
    val name: String?,
    val username: String?,
    val favoritesCount: Int?,
    val followedCount: Int?,
    val followingCount: Int?,
    val isFollowing: Boolean?,
    val likeCount: Int?,
    val recipeCount: Int?
):Parcelable