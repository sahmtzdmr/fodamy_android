package com.sadikahmetozdemir.data.shared.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserImageModel(
    val width: Int,
    val height: Int,
    val key: String,
    val order: Int,
    val url: String
) : Parcelable