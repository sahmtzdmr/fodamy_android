package com.sadikahmetozdemir.domain.repositories

import com.sadikahmetozdemir.domain.entities.Auth
import com.sadikahmetozdemir.domain.entities.LoginRequest
import com.sadikahmetozdemir.domain.entities.LoginResponseModel
import com.sadikahmetozdemir.domain.entities.Logout
import com.sadikahmetozdemir.domain.entities.RegisterRequest
import com.sadikahmetozdemir.domain.requests.Resource

interface AuthRepository {
    suspend fun loginRequest(loginRequest: LoginRequest): Resource<LoginResponseModel>?
    suspend fun registerRequest(registerRequest: RegisterRequest): Resource<Auth>
    suspend fun logoutRequest(): Resource<Logout>

}