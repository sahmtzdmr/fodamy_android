package com.sadikahmetozdemir.data.shared.local.converters

import android.content.Context
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.sadikahmetozdemir.data.shared.local.dto.ImageDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@ProvidedTypeConverter
class ImageConverter @Inject constructor() {

    @TypeConverter
    fun imageDbToJson(imageDb: ImageDatabase): String {
        return toJson<ImageDatabase>(imageDb)
    }

    @TypeConverter
    fun jsonToImageDb(imageDbSrc: String): ImageDatabase {
        return fromJson<ImageDatabase>(imageDbSrc)
    }
}