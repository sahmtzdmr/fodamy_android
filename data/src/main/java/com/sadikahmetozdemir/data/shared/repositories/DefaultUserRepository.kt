package com.sadikahmetozdemir.data.shared.repositories

import com.sadikahmetozdemir.data.service.UserAPI
import com.sadikahmetozdemir.domain.repositories.UserRepository
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(private val userAPI: UserAPI) : UserRepository,
    BaseRepository() {
    override suspend fun userProfileRequest() {

    }

}