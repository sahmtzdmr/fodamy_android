package com.sadikahmetozdemir.data.shared.remote

import com.google.gson.annotations.SerializedName
import com.sadikahmetozdemir.data.shared.local.UserImageModel

data class UserProfileModel(
    @SerializedName("favorites_count")
    val favoritesCount: Int,
    @SerializedName("followed_count")
    val followedCount: Int,
    @SerializedName("following_count")
    val followingCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: UserImageModel?,
    @SerializedName("is_following")
    val isFollowing: Boolean,
    @SerializedName("is_top_user_choice")
    val isTopUserChoice: Boolean,
    @SerializedName("is_trusted")
    val isTrusted: Int,
    @SerializedName("likes_count")
    val likesCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("recipe_count")
    val recipeCount: Int,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("username")
    val username: String
)