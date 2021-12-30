package com.sadikahmetozdemir.sadik_fodamy.shared.remote

import com.google.gson.annotations.SerializedName

data class Result(
    @field:SerializedName("error")
    val error: String = String(),
    @field:SerializedName("code")
    val code: String = String(),
    @field:SerializedName("message")
    val message: String = String(),

)
