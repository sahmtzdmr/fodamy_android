package com.sadikahmetozdemir.data.shared.local

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("id")
    var id: Int,
    @SerializedName("email")
    var email: String?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("surname")
    var surname: String?,
    @SerializedName("definition")
    var definition: String?,
    @SerializedName("is_trusted")
    var isTrusted: Int?,
    @SerializedName("followed_count")
    var followedCount: Int?,
    @SerializedName("following_count")
    var followingCount: Int?,
    @SerializedName("recipe_count")
    var recipeCount: Int?,
    @SerializedName("is_following")
    var isFollowing: Boolean,
    @SerializedName("favorites_count")
    var favoritesCount: Int?,
    @SerializedName("likes_count")
    var likesCount: Int?,
    var image: ImagesModel?

)
