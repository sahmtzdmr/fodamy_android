package com.sadikahmetozdemir.data.shared.repositories

import com.sadikahmetozdemir.data.mappers.toDomainModel
import com.sadikahmetozdemir.data.service.ApiErrorResponse
import com.sadikahmetozdemir.data.service.ApiResponse
import com.sadikahmetozdemir.data.service.ApiSuccessResponse
import com.sadikahmetozdemir.data.service.LoginAPI
import com.sadikahmetozdemir.data.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.domain.utils.constants.NETWORK_ERROR_MESSAGE
import com.sadikahmetozdemir.domain.entities.Auth
import com.sadikahmetozdemir.domain.entities.LoginRequest
import com.sadikahmetozdemir.domain.entities.LoginResponseModel
import com.sadikahmetozdemir.domain.entities.Logout
import com.sadikahmetozdemir.domain.entities.RegisterRequest
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import com.sadikahmetozdemir.domain.requests.Resource
import com.sadikahmetozdemir.domain.requests.Result
import java.io.IOException
import javax.inject.Inject

abstract class DefaultAuthRepository @Inject constructor(private val loginAPI: LoginAPI) :
    AuthRepository {

    override suspend fun loginRequest(loginRequest: LoginRequest): Resource<LoginResponseModel> {
        return try {
            val response = loginAPI.loginRequest(loginRequest)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success(apiResponse.body.toDomainModel())
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

    override suspend fun registerRequest(registerRequest: RegisterRequest): Resource<Auth> {
        return try {
            val response = loginAPI.registerRequest(registerRequest)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body).toDomainModel())
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

    override suspend fun logoutRequest(): Resource<Logout> {
        return try {
            val response = loginAPI.logoutRequest()
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body).toDomainModel())
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
