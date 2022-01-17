package com.sadikahmetozdemir.data.shared.repositories

import java.io.IOException
import com.sadikahmetozdemir.data.service.ApiErrorResponse
import com.sadikahmetozdemir.data.service.ApiResponse
import com.sadikahmetozdemir.data.service.ApiSuccessResponse
import com.sadikahmetozdemir.data.service.LoginAPI
import com.sadikahmetozdemir.data.shared.remote.LoginRequestModel
import com.sadikahmetozdemir.data.shared.remote.LoginResponseModel
import com.sadikahmetozdemir.data.shared.remote.LogoutModel
import com.sadikahmetozdemir.data.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.data.shared.remote.RegisterResponseModel
import com.sadikahmetozdemir.data.shared.remote.Resource
import com.sadikahmetozdemir.data.shared.remote.Result
import com.sadikahmetozdemir.data.shared.utils.NETWORK_ERROR_MESSAGE
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(private val loginAPI: LoginAPI): AuthRepository {

    override suspend fun loginRequest(loginRequestModel: LoginRequestModel): Resource<LoginResponseModel>? {
        return try {
            val response = loginAPI.loginRequest(loginRequestModel)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success(apiResponse.body)
                }
                is ApiErrorResponse -> {
                    Resource.error(apiResponse.errorMessage)
                }
                else -> Resource.error(Result())
            }
        } catch (exception: IOException) {
            val apiException = ApiException.create(exception)
            Resource.error(apiException, null)
        }
    }
    override suspend fun registerRequest(registerRequestModel: RegisterRequestModel): Resource<RegisterResponseModel> {
        return try {
            val response = loginAPI.registerRequest(registerRequestModel)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body))
                }
                is ApiErrorResponse -> {
                    Resource.error(apiResponse.errorMessage)
                }
                else -> Resource.error(Result())
            }
        } catch (exception: IOException) {
            val apiException = ApiException.create(exception)
            Resource.error(apiException, null)
        }
    }

    override suspend fun logoutRequest(): Resource<LogoutModel> {
        return try {
            val response = loginAPI.logoutRequest()
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body))
                }
                is ApiErrorResponse -> {
                    Resource.error(apiResponse.errorMessage)
                }
                else -> Resource.error(Result())
            }
        } catch (exception: IOException) {
            val apiException = ApiException.create(exception)
            Resource.error(apiException, null)
        }
    }
}

class ApiException {

    companion object {
        fun create(error: Exception): Result = NETWORK_ERROR_MESSAGE
    }
}
