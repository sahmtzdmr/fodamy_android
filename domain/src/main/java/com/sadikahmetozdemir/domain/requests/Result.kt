package com.sadikahmetozdemir.domain.requests

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class Result(
    @field:SerializedName("error")
    val error: String = String(),
    @field:SerializedName("code")
    val code: String = String(),
    @field:SerializedName("message")
    val message: String = String(),
    )