package com.sadikahmetozdemir.data.shared.remote

import com.google.gson.annotations.SerializedName
import com.sadikahmetozdemir.data.shared.local.PaginationModel


data class FavoritesResponseModel(
    @SerializedName("data")
    var data: List<CategoryModel>?,
    @SerializedName("pagination")
    var pagination: PaginationModel?
)
