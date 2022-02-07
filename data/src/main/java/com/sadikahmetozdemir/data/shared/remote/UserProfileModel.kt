package com.sadikahmetozdemir.data.shared.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProfileModel(
    @SerializedName("favorites_count")
    val favoritesCount: Int,
    @SerializedName("followed_count")
    val followedCount: Int,
    @SerializedName("following_count")
    val followingCount: Int,
    val id: Int,
    val image: String,
    @SerializedName("is_following")
    val isFollowing: Boolean,
    @SerializedName("is_top_user_choice")
    val isTopUserChoice: Boolean,
    @SerializedName("is_trusted")
    val isTrusted: Int,
    @SerializedName("likes_count")
    val likesCount: Int,
    val name: String,
    @SerializedName("recipe_count")
    val recipeCount: Int,
    val surname: String,
    val username: String
) : Parcelable