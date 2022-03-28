package com.sadikahmetozdemir.data.shared.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.sadikahmetozdemir.data.shared.local.dto.ImageDatabase

@ProvidedTypeConverter
class ImageListConverter {
    @TypeConverter
    fun imageListToJson(images: List<ImageDatabase>): String {
        return toJson<List<ImageDatabase>>(images)
    }

    @TypeConverter
    fun jsonToImageList(imageListSrc: String): List<ImageDatabase> {
        return fromJson(imageListSrc)
    }
}