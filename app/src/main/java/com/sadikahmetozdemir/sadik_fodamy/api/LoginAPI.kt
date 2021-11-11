package com.sadikahmetozdemir.sadik_fodamy.api

import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel
import retrofit2.Call
import retrofit2.http.POST

interface LoginAPI {


    @POST("/api/auth/login")
    fun post(): Call<List<UserModel>>

}