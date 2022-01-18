package com.sadikahmetozdemir.data.shared.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: Int,
    var email: String,
    var username: String,
    var name: String,
    var surname: String,
    @SerializedName("is_banned")
    var isBanned: Int,
    var is_trusted: Int,
    @SerializedName("followed_count")
    var followedCount: Int,
    @SerializedName("following_count")
    var followingCount: Int,
    @SerializedName("recipe_count")
    var recipeCount: Int,
    @SerializedName("is_following")
    var isFollowing: Boolean,
    @SerializedName("favorites_count")
    var favoritesCount: Int,
    @SerializedName("likes_count")
    var likesCount: Int,
    var image: ImagesModel

) : Parcelable
