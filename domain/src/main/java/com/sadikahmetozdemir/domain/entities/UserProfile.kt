package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProfile(
    val favoritesCount: Int,
    val followedCount: Int,
    val followingCount: Int,
    val image: Images?,
    val id: Int,
    val isFollowing: Boolean,
    val isTopUserChoice: Boolean,
    val likesCount: Int,
    val username: String,
    val recipeCount: Int
    ) : Parcelable

