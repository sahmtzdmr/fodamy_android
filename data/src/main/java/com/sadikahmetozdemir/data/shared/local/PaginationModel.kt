package com.sadikahmetozdemir.data.shared.local

import com.google.gson.annotations.SerializedName


data class PaginationModel(
    @SerializedName("total")
    var total: Int?,
    @SerializedName("per_page")
    var perPage: Int?,
    @SerializedName("current_page")
    var currentPage: Int?,
    @SerializedName("last_page")
    var lastPage: Int?,
    @SerializedName("first_item")
    var firstItem: Int?,
    @SerializedName("last_item")
    var lastItem: Int?,
)
