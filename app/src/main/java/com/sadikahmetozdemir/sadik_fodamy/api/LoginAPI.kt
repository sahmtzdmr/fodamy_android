package com.sadikahmetozdemir.sadik_fodamy.api

import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAPI {


    @POST("/api/auth/login")
    fun loginRequest(@Body loginRequestModel: LoginRequestModel): Call<LoginResponseModel>


    @POST("api/auth/register")
    fun registerRequest(@Body registerRequestModel: RegisterRequestModel): Call<RegisterResponseModel>

  

}
