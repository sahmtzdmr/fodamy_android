package com.sadikahmetozdemir.sadik_fodamy.utils

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(private val sharedPreferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val userToken = sharedPreferences.getString(SharedPreferanceStorage.PREFS_USER_TOKEN, "")
        if (userToken.isNullOrBlank()) {
            return chain.proceed(request.build())
        }
        request.addHeader("X-Fodamy-Token", userToken)
        return chain.proceed(request.build())
    }
}
