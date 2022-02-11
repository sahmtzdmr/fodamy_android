package com.sadikahmetozdemir.data.shared.remote

import com.google.gson.annotations.SerializedName
import com.sadikahmetozdemir.data.shared.local.ImagesModel


data class CategoryModel(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("recipes")
    var recipes: List<EditorChoiceModel>?,
    @SerializedName("image")
    var image: ImagesModel?
)
