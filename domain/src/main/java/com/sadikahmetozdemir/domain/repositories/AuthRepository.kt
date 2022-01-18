package com.sadikahmetozdemir.domain.repositories

import com.sadikahmetozdemir.data.shared.remote.LoginRequestModel
import com.sadikahmetozdemir.data.shared.remote.LogoutModel
import com.sadikahmetozdemir.data.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.data.shared.remote.RegisterResponseModel
import com.sadikahmetozdemir.data.shared.remote.Resource

interface AuthRepository{
    suspend fun loginRequest(loginRequestModel: LoginRequestModel): Resource<com.sadikahmetozdemir.domain.entities.LoginResponseModel>?
    suspend fun registerRequest(registerRequestModel: RegisterRequestModel): Resource<RegisterResponseModel>
    suspend fun logoutRequest(): Resource<LogoutModel>

}