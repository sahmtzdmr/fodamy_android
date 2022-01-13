package com.sadikahmetozdemir.sadik_fodamy.shared.repositories
import com.sadikahmetozdemir.sadik_fodamy.api.ApiErrorResponse
import com.sadikahmetozdemir.sadik_fodamy.api.ApiResponse
import com.sadikahmetozdemir.sadik_fodamy.api.ApiSuccessResponse
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LoginRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LoginResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LogoutModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Resource
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Result
import com.sadikahmetozdemir.sadik_fodamy.utils.NETWORK_ERROR_MESSAGE
import java.io.IOException
import javax.inject.Inject

interface AuthRepository{
    suspend fun loginRequest(loginRequestModel: LoginRequestModel): Resource<LoginResponseModel>?
    suspend fun registerRequest(registerRequestModel: RegisterRequestModel): Resource<RegisterResponseModel>
    suspend fun logoutRequest(): Resource<LogoutModel>

}

class DefaultAuthRepository @Inject constructor(private val loginAPI: LoginAPI) {

    suspend fun loginRequest(loginRequestModel: LoginRequestModel): Resource<LoginResponseModel>? {
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
    suspend fun registerRequest(registerRequestModel: RegisterRequestModel): Resource<RegisterResponseModel> {
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

    suspend fun logoutRequest(): Resource<LogoutModel> {
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
