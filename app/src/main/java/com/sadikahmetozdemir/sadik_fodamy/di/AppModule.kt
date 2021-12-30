package com.sadikahmetozdemir.sadik_fodamy.di

import android.content.Context
import android.content.SharedPreferences
import com.sadikahmetozdemir.sadik_fodamy.BuildConfig
import com.sadikahmetozdemir.sadik_fodamy.api.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.shared.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.utils.NetworkInterceptor
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class, FragmentComponent::class, ActivityRetainedComponent::class)
object AppModule {
    @Provides
    fun provideSharedPrefs(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context?.getSharedPreferences(
            "PREFS",
            Context.MODE_PRIVATE
        )
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SharedPreferanceStorage.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAuthService(retrofitClient: Retrofit) = retrofitClient.create(LoginAPI::class.java)

    @Provides
    fun provideFeedService(retrofitClient: Retrofit) =
        retrofitClient.create(EditorChoiceRecipesAPI::class.java)

    @Provides
    fun provideFeedRepository(editorChoiceRecipesAPI: EditorChoiceRecipesAPI) =
        FeedRepository(editorChoiceRecipesAPI)

    @Provides
    fun provideInterceptor(networkInterceptor: NetworkInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}

