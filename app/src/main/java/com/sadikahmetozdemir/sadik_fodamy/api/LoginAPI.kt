package com.sadikahmetozdemir.sadik_fodamy.api

import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAPI {


    @POST("/api/auth/login")
    suspend fun loginRequest(@Body loginRequestModel: LoginRequestModel): Response<LoginResponseModel>


    @POST("api/auth/register")
    suspend fun registerRequest(@Body registerRequestModel: RegisterRequestModel): Response<RegisterResponseModel>

  

}
