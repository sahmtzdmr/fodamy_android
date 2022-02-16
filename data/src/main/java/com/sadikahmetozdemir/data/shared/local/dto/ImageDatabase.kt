package com.sadikahmetozdemir.data.shared.local.dto

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageDatabase(
    val height: Int,
    val url: String,
    val image: Bitmap?,
    val width: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}