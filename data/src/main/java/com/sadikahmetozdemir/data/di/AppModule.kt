package com.sadikahmetozdemir.data.di


import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.data.service.LoginAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideAuthService(retrofitClient: Retrofit) = retrofitClient.create(LoginAPI::class.java)

    @Provides
    fun provideFeedService(retrofitClient: Retrofit) =
        retrofitClient.create(EditorChoiceRecipesAPI::class.java)

}