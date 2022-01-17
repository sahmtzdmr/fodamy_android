package com.sadikahmetozdemir.data.shared.remote

import android.os.Parcelable
import com.sadikahmetozdemir.data.shared.local.ImagesModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoritesCategoryModel(
    var id: Int,
    var name: String,
    var recipes: List<EditorChoiceModel>,
    var image: ImagesModel?
) : Parcelable
