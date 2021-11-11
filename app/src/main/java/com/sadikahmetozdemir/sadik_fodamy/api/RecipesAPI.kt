package com.sadikahmetozdemir.sadik_fodamy.api

import com.sadikahmetozdemir.sadik_fodamy.shared.local.LikeResponse
import com.sadikahmetozdemir.sadik_fodamy.shared.local.RecipesModel
import retrofit2.Call
import retrofit2.http.GET

interface RecipesAPI {


    @GET("api/user/2/like")
    fun getData() : Call<LikeResponse>
}