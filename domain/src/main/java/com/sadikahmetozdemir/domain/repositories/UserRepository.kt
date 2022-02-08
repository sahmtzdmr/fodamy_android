package com.sadikahmetozdemir.domain.repositories

import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.entities.UserProfile

interface UserRepository {

    suspend fun userProfileRequest(userID: Int): UserProfile
    suspend fun userLikesRequest(userID: Int, page: Int = 1): List<Recipe>
}