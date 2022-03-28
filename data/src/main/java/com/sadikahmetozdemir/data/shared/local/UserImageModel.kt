package com.sadikahmetozdemir.data.shared.local

import com.google.gson.annotations.SerializedName

data class UserImageModel(
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("key")
    val key: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("url")
    val url: String
)