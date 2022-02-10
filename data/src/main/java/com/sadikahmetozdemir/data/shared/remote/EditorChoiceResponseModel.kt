package com.sadikahmetozdemir.data.shared.remote

import com.google.gson.annotations.SerializedName
import com.sadikahmetozdemir.data.shared.local.PaginationModel


data class EditorChoiceResponseModel(
    @SerializedName("data")
    var data: List<EditorChoiceModel>?,
    @SerializedName("pagination")
    var pagination: PaginationModel?
)
