package com.sadikahmetozdemir.data.shared.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ImagesModel(
    var width: Int,
    var height: Int,
    var key: String,
    var order: Int,
    var url: String
) : Parcelable
