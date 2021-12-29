package com.sadikahmetozdemir.sadik_fodamy.shared.remote

import android.os.Parcelable
import com.sadikahmetozdemir.sadik_fodamy.shared.local.ImagesModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoritesCategoryModel(
    var id: Int,
    var name: String,
    var recipes: List<EditorChoiceModel>,
    var image: ImagesModel?
) : Parcelable
