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

 class DefaultAuthRepository @Inject constructor(private val loginAPI: LoginAPI) :
    AuthRepository,BaseRepository() {

    override suspend fun loginRequest(loginRequest: LoginRequest): LoginResponseModel {
        return execute { loginAPI.loginRequest(loginRequest).body()?.toDomainModel()!! }
    }

    override suspend fun registerRequest(registerRequest: RegisterRequest):Auth {
        return execute { loginAPI.registerRequest(registerRequest).body()?.toDomainModel()!! }
    }

    override suspend fun logoutRequest():Logout{
        return execute { loginAPI.logoutRequest().body()?.toDomainModel()!! }
    }
}

class ApiException {

    companion object {
        fun create(error: Exception): Result = NETWORK_ERROR_MESSAGE
    }
}
