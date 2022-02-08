package com.sadikahmetozdemir.data.service

import com.sadikahmetozdemir.data.shared.remote.EditorChoiceResponseModel
import com.sadikahmetozdemir.data.shared.remote.UserProfileModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserAPI {

    @GET("api/user/{user_id}")
    suspend fun userProfileRequest(@Path("user_id") userId: Int): UserProfileModel

    @GET("api/user/{user_id}/like")
    suspend fun userLikesRequest(
        @Path("user_id") userId: Int,
        @Query("page") page: Int
    ): EditorChoiceResponseModel

}