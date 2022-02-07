package com.sadikahmetozdemir.domain.repositories

import com.sadikahmetozdemir.domain.entities.UserProfile

interface UserRepository {
    suspend fun userProfileRequest(userID: Int): UserProfile
}