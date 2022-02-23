package com.sadikahmetozdemir.data.shared.local

import com.google.gson.annotations.SerializedName


data class ImagesModel(
    @SerializedName("width")
    var width: Int,
    @SerializedName("height")
    var height: Int,
    @SerializedName("key")
    var key: String?,
    @SerializedName("order")
    var order: Int?,
    @SerializedName("url")
    var url: String?
)
