package com.sadikahmetozdemir.data.service


import com.sadikahmetozdemir.data.shared.remote.LoginResponseModel
import com.sadikahmetozdemir.data.shared.remote.LogoutModel
import com.sadikahmetozdemir.data.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.data.shared.remote.RegisterResponseModel
import com.sadikahmetozdemir.domain.entities.LoginRequest
import com.sadikahmetozdemir.domain.entities.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("/api/auth/login")
    suspend fun loginRequest(@Body loginRequest: LoginRequest): Response<LoginResponseModel>

    @POST("api/auth/register")
    suspend fun registerRequest(@Body registerRequest: RegisterRequest): Response<RegisterResponseModel>

    @POST("api/auth/logout")
    suspend fun logoutRequest(): Response<LogoutModel>
}
