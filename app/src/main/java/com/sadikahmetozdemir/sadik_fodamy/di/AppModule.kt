package com.sadikahmetozdemir.sadik_fodamy.di

import DefaultFeedRepository
import android.content.Context
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.data.service.LoginAPI
import com.sadikahmetozdemir.data.service.RecipeDao
import com.sadikahmetozdemir.data.service.UserAPI
import com.sadikahmetozdemir.data.service.UserDao
import com.sadikahmetozdemir.data.shared.repositories.DefaultAuthRepository
import com.sadikahmetozdemir.data.shared.repositories.DefaultUserRepository
import com.sadikahmetozdemir.data.utils.DataHelperManager
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
    fun provideFeedRepository(
        editorChoiceRecipesAPI: EditorChoiceRecipesAPI,
        recipeDao: RecipeDao
    ): FeedRepository {
        return DefaultFeedRepository(editorChoiceRecipesAPI, recipeDao)
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
