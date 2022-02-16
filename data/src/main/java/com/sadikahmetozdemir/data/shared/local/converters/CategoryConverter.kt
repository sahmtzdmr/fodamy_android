package com.sadikahmetozdemir.data.shared.local.converters

import androidx.room.TypeConverter
import com.sadikahmetozdemir.data.shared.local.dto.CategoryDatabase

class CategoryConverter {

    @TypeConverter
    fun categoryToJson(category: CategoryDatabase): String {
        return toJson<CategoryDatabase>(category)
    }

    @TypeConverter
    fun jsonToCategory(category: String): CategoryDatabase {
        return fromJson<CategoryDatabase>(category)
    }
}