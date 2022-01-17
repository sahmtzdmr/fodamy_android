package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val height: Int?,
    val url: String?,
    val width: Int?
) : Parcelable