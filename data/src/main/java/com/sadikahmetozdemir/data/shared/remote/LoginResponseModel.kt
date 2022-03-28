package com.sadikahmetozdemir.data.shared.remote

import com.google.gson.annotations.SerializedName
import com.sadikahmetozdemir.data.shared.local.User


data class LoginResponseModel(
    @SerializedName("token")
    var token: String?,
    @SerializedName("user")
    var user: User?)
