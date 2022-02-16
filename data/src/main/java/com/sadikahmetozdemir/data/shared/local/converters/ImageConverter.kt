package com.sadikahmetozdemir.data.shared.local.converters

import android.content.Context
import android.graphics.Bitmap
import androidx.room.ProvidedTypeConverter
import com.bumptech.glide.Glide
import com.sadikahmetozdemir.data.shared.local.dto.ImageDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@ProvidedTypeConverter
class ImageConverter @Inject constructor(@ApplicationContext private val context: Context) {
    private fun urlToBitmap(imageDb: ImageDatabase): Bitmap {
        return Glide.with(context)
            .asBitmap()
            .load(imageDb.url)
            .submit(imageDb.width, imageDb.height).get()
    }
}