package com.sadikahmetozdemir.data.shared.remote

import com.google.gson.annotations.SerializedName
import com.sadikahmetozdemir.data.shared.local.PaginationModel


data class CommentResponseModel(
    @SerializedName("data")
    var data: List<CommentModel>,
    @SerializedName("pagination")
    var pagination: PaginationModel?
)
