package com.sadikahmetozdemir.sadik_fodamy.di

import android.content.Context
import android.content.SharedPreferences
import com.sadikahmetozdemir.sadik_fodamy.api.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.utils.SharedPreferanceStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class, FragmentComponent::class,ActivityRetainedComponent::class)
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
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SharedPreferanceStorage.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAuthService(retrofitClient: Retrofit) = retrofitClient.create(LoginAPI::class.java)

    @Provides
    fun provideFeedService(retrofitClient: Retrofit) = retrofitClient.create(EditorChoiceRecipesAPI::class.java)



}