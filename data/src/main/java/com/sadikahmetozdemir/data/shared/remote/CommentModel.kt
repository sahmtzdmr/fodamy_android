package com.sadikahmetozdemir.data.shared.remote

import com.google.gson.annotations.SerializedName
import com.sadikahmetozdemir.data.shared.local.User


data class CommentModel(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("text")
    var text: String?,
    @SerializedName("difference")
    var difference: String?,
    @SerializedName("user")
    var user: User?,
)
