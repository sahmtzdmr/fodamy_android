package com.sadikahmetozdemir.data.service

import com.sadikahmetozdemir.data.shared.remote.UserProfileModel
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPI {

    @GET("api/user/{user_id}")
    suspend fun userProfileRequest(@Path("user_id") userId: Int): UserProfileModel

}