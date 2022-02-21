package com.sadikahmetozdemir.data.shared.local.dto

import android.graphics.Bitmap


data class ImageDatabase(
    val height: Int?,
    val url: String?,
    val width: Int?,
    val image: Bitmap? = null
) {
    companion object {
    val empty = ImageDatabase(0,"",0)
    }
}
