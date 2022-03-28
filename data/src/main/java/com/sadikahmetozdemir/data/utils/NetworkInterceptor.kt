package com.sadikahmetozdemir.data.utils

import com.sadikahmetozdemir.data.di.InterceptorScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(
    @InterceptorScope
    private val scope: CoroutineScope,
    private val dataHelperManager: DataHelperManager,
) : Interceptor {
    private var token: String? = null
    override fun intercept(chain: Interceptor.Chain): Response {
        getToken()
        val request = chain.request().newBuilder()
        if (token.isNullOrBlank()) {
            return chain.proceed(request.build())
        }
        request.addHeader("X-Fodamy-Token", token.toString())
        return chain.proceed(request.build())
    }

    fun getToken() {
        scope.launch {
            token = dataHelperManager.getToken()
        }
    }
}