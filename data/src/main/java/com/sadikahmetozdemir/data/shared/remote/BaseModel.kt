package com.sadikahmetozdemir.data.shared.remote

import com.google.gson.annotations.SerializedName

data class BaseModel(
    @SerializedName("code")
    var code: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("error")
    var error: String?
)
