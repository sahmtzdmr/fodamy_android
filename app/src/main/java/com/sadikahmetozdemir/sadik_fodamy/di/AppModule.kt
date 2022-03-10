package com.sadikahmetozdemir.sadik_fodamy.di

import DefaultFeedRepository
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.sadikahmetozdemir.data.service.LoginAPI
import com.sadikahmetozdemir.data.service.RecipesAPI
import com.sadikahmetozdemir.data.service.UserAPI
import com.sadikahmetozdemir.data.service.dao.CommentDao
import com.sadikahmetozdemir.data.service.dao.RecipeDao
import com.sadikahmetozdemir.data.service.dao.UserDao
import com.sadikahmetozdemir.data.shared.local.database.AppDatabase
import com.sadikahmetozdemir.data.shared.repositories.DefaultAuthRepository
import com.sadikahmetozdemir.data.shared.repositories.DefaultUserRepository
import com.sadikahmetozdemir.data.shared.repositories.MockRepository
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.data.utils.JsonReader
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

val mockFlag = false


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDataManager(@ApplicationContext context: Context): DataHelperManager {
        return DataHelperManager(context)
    }

    @Provides
    @ExperimentalPagingApi
    fun provideFeedRepository(
        recipesAPI: RecipesAPI,
        recipeDao: RecipeDao,
        commentDao: CommentDao,
        appDatabase: AppDatabase,
        jsonReader: JsonReader
    ): FeedRepository {
        if (mockFlag) {
            return MockRepository(jsonReader)
        } else {
            return DefaultFeedRepository(recipesAPI, recipeDao, commentDao, appDatabase)
        }

    }

    @Provides
    fun provideAuthRepository(loginAPI: LoginAPI): AuthRepository {
        return DefaultAuthRepository(loginAPI)
    }

    @Provides
    @Singleton
    @ApplicationScope
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }


    @Provides
    @Singleton
    fun provideUserRepository(userAPI: UserAPI, userDao: UserDao): UserRepository {
        return DefaultUserRepository(userAPI, userDao)
    }
}
