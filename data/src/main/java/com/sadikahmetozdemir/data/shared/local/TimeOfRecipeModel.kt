package com.sadikahmetozdemir.data.shared.local

import com.google.gson.annotations.SerializedName


data class TimeOfRecipeModel(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("text")
    var text: String?
)
