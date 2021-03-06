package com.sadikahmetozdemir.data.shared.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sadikahmetozdemir.data.service.dao.CommentDao
import com.sadikahmetozdemir.data.service.dao.RecipeDao
import com.sadikahmetozdemir.data.service.dao.RemoteKeyDao
import com.sadikahmetozdemir.data.service.dao.UserDao
import com.sadikahmetozdemir.data.shared.local.converters.CategoryConverter
import com.sadikahmetozdemir.data.shared.local.converters.ImageConverter
import com.sadikahmetozdemir.data.shared.local.converters.ImageListConverter
import com.sadikahmetozdemir.data.shared.local.converters.RecipeListConverter
import com.sadikahmetozdemir.data.shared.local.converters.UserConverter
import com.sadikahmetozdemir.data.shared.local.dto.CategoryDatabase
import com.sadikahmetozdemir.data.shared.local.dto.CommentDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RecipeDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RemoteKeyCommentDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RemoteKeyEditorChoiceDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RemoteKeyLastAddedDatabase
import com.sadikahmetozdemir.data.shared.local.dto.UserDatabase

@Database(
    entities = [
        RecipeDatabase::class,
        CategoryDatabase::class,
        CommentDatabase::class,
        UserDatabase::class,
        RemoteKeyEditorChoiceDatabase::class,
        RemoteKeyLastAddedDatabase::class,
        RemoteKeyCommentDatabase::class
    ],
    version = 18

)
@TypeConverters(
    UserConverter::class,
    CategoryConverter::class,
    ImageConverter::class,
    RecipeListConverter::class,
    ImageListConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun userDao(): UserDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun commentDao(): CommentDao
}