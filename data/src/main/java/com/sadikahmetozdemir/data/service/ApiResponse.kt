package com.sadikahmetozdemir.data.service

import com.google.gson.Gson
import com.sadikahmetozdemir.domain.requests.Result
import com.sadikahmetozdemir.data.shared.utils.ERROR_MESSAGE
import okhttp3.ResponseBody
import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(Result(error.message!!))
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else if (response.body() is Result) {
                    ApiSuccessResponse(body, (response.body() as Result).code)
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val errorMessage =
                    Gson().fromJson(response.errorBody()?.string(), Result::class.java)
                ApiErrorResponse((errorMessage ?: ERROR_MESSAGE) as Result, errorMessage.code)
            } /*else{
                ApiErrorResponse(Result("unknown error"))
            }*/
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T, val code: String = String()) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: Result, val code: String = String()) :
    ApiResponse<T>()

data class ApiRedirectResponse<T>(val body: ResponseBody) : ApiResponse<T>()
