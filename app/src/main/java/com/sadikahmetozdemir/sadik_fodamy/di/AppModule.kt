package com.sadikahmetozdemir.sadik_fodamy.di

import DefaultFeedRepository
import android.content.Context
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.data.service.LoginAPI
import com.sadikahmetozdemir.data.shared.repositories.DefaultAuthRepository
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class, FragmentComponent::class, ActivityRetainedComponent::class)
object AppModule {

    @Provides
    fun provideDataManager(@ApplicationContext context: Context): DataHelperManager {
        return DataHelperManager(context)
    }
    @Provides
    fun provideFeedRepository(editorChoiceRecipesAPI: EditorChoiceRecipesAPI):FeedRepository{
        return DefaultFeedRepository(editorChoiceRecipesAPI)
    }
    @Provides
    fun provideAuthRepository(loginAPI: LoginAPI):AuthRepository{
        return DefaultAuthRepository(loginAPI)
    }

    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
}
