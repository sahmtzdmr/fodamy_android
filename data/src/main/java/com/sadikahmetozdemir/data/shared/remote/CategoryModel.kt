package com.sadikahmetozdemir.data.shared.remote

import android.os.Parcelable
import com.sadikahmetozdemir.data.shared.local.ImagesModel
import kotlinx.parcelize.Parcelize


@Parcelize
data class CategoryModel(
    var id: Int?,
    var name: String?,
    var recipes: List<EditorChoiceModel>?,
    var image: ImagesModel?
) : Parcelable
