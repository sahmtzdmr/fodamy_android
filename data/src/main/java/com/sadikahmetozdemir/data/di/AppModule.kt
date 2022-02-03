package com.sadikahmetozdemir.data.di


import com.sadikahmetozdemir.data.BuildConfig
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.data.service.LoginAPI
import com.sadikahmetozdemir.data.shared.utils.BASE_URL
import com.sadikahmetozdemir.data.utils.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    @Singleton
    fun provideAuthService(retrofitClient: Retrofit) = retrofitClient.create(LoginAPI::class.java)

    @Provides
    @Singleton
    fun provideFeedService(retrofitClient: Retrofit) =
        retrofitClient.create(EditorChoiceRecipesAPI::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
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
    @Provides
    @Singleton
    @InterceptorScope
    fun provideScope():CoroutineScope{
        return CoroutineScope(SupervisorJob()+Dispatchers.IO)
    }





}
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class InterceptorScope