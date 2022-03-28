package com.sadikahmetozdemir.data.shared.repositories


import com.google.gson.Gson
import com.sadikahmetozdemir.data.shared.exceptions.SimpleHttpException
import com.sadikahmetozdemir.data.shared.exceptions.UnauthorizedException
import com.sadikahmetozdemir.domain.requests.Result
import retrofit2.HttpException


abstract class BaseRepository() {

    suspend fun <T> execute(request: suspend () -> T): T {
        return try {
            request.invoke()
        } catch (exception: Exception) {
            throw parseException(exception)
        }
    }

    open suspend fun <T> fetchFromLocal(cached: suspend () -> T?): T? {
        return try {
            cached.invoke()
        } catch (ex: Exception) {
            throw parseException(ex)
        }
    }

    open suspend fun saveToLocal(store: suspend () -> Unit) {
        try {
            store.invoke()
        } catch (ex: Exception) {
            throw parseException(ex)
        }
    }


    private fun parseException(exception: Exception): Exception {
        return when (exception) {
            is HttpException -> {
                try {
                    if (exception.code() == UNAUTHORIZED_CODE) {
                        return UnauthorizedException()
                    }
                    val response = Gson().fromJson(
                        exception.response()!!.errorBody()!!.string(),
                        Result::class.java
                    )
                    requireNotNull(response)
                    requireNotNull(response.code)
                    requireNotNull(response.message)
                    SimpleHttpException(
                        response.code,
                        response.error
                    )
                } catch (e: Exception) {
                    exception
                }
            }
            else -> exception.also {
                it.printStackTrace()
            }
        }
    }

    companion object {
        private const val AUTHENTICATION_CODE = 403
        private const val UNAUTHORIZED_CODE = 401
    }
}
