package com.sadikahmetozdemir.data.di

import android.content.Context
import androidx.room.Room
import com.sadikahmetozdemir.data.BuildConfig
import com.sadikahmetozdemir.data.service.dao.CommentDao
import com.sadikahmetozdemir.data.service.dao.RecipeDao
import com.sadikahmetozdemir.data.service.dao.UserDao
import com.sadikahmetozdemir.data.shared.local.converters.ImageConverter
import com.sadikahmetozdemir.data.shared.local.converters.ImageListConverter
import com.sadikahmetozdemir.data.shared.local.converters.RecipeListConverter
import com.sadikahmetozdemir.data.shared.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRecipeDao(appDatabase: AppDatabase): RecipeDao {
        return appDatabase.recipeDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideCommentDao(appDatabase: AppDatabase): CommentDao {
        return appDatabase.commentDao()
    }

    @Provides
    @Singleton
    fun provideImageListConverter(): ImageListConverter {
        return ImageListConverter()
    }

    @Provides
    @Singleton
    fun provideJsonConverter(): RecipeListConverter {
        return RecipeListConverter()
    }

    @Provides
    @Singleton
    fun provideImageConverter(): ImageConverter {
        return ImageConverter()
    }


    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        imageConverter: ImageConverter,
        imageListConverter: ImageListConverter,

        ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            BuildConfig.DBNAME
        ).fallbackToDestructiveMigration()
            .addTypeConverter(imageConverter)
            .addTypeConverter(imageListConverter)
            .build()
    }

}