package com.sadikahmetozdemir.sadik_fodamy.api

import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LoginRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LoginResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.LogoutModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.RegisterResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("/api/auth/login")
    suspend fun loginRequest(@Body loginRequestModel: LoginRequestModel): Response<LoginResponseModel>

    @POST("api/auth/register")
    suspend fun registerRequest(@Body registerRequestModel: RegisterRequestModel): Response<RegisterResponseModel>
    @POST("api/auth/logout")
    suspend fun logoutRequest(): Response<LogoutModel>
}
