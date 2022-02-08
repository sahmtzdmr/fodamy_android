package com.sadikahmetozdemir.data.shared.repositories

import com.sadikahmetozdemir.data.mappers.toDomainModel
import com.sadikahmetozdemir.data.mappers.toDomaninModel
import com.sadikahmetozdemir.data.service.UserAPI
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.entities.UserProfile
import com.sadikahmetozdemir.domain.repositories.UserRepository
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(private val userAPI: UserAPI) : UserRepository,
    BaseRepository() {
    override suspend fun userProfileRequest(userID: Int): UserProfile {
        return execute { userAPI.userProfileRequest(userID).toDomainModel() }

    }

    override suspend fun userLikesRequest(userID: Int, page: Int): List<Recipe> {
        return execute {
            userAPI.userLikesRequest(userID, page).data?.map { it.toDomaninModel() } }!!

    }


}