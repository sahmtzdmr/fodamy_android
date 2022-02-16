package com.sadikahmetozdemir.data.shared.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sadikahmetozdemir.data.service.RecipeDao
import com.sadikahmetozdemir.data.service.UserDao
import com.sadikahmetozdemir.data.shared.local.converters.CategoryConverter
import com.sadikahmetozdemir.data.shared.local.converters.ImageConverter
import com.sadikahmetozdemir.data.shared.local.converters.JsonConverter
import com.sadikahmetozdemir.data.shared.local.converters.UserConverter
import com.sadikahmetozdemir.data.shared.local.dto.CategoryDatabase
import com.sadikahmetozdemir.data.shared.local.dto.ImageDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RecipeDataBase

@Database(
    entities = [
        RecipeDataBase::class,
        CategoryDatabase::class,
        ImageDatabase::class,
    ],
    version = 5,
    exportSchema = false
)
@TypeConverters(
    UserConverter::class,
    CategoryConverter::class,
    ImageConverter::class,
    JsonConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun userDao(): UserDao
}