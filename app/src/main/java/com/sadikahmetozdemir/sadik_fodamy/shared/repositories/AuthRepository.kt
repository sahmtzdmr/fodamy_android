package com.sadikahmetozdemir.sadik_fodamy.shared.repositories

import android.content.SharedPreferences
import com.sadikahmetozdemir.sadik_fodamy.api.ApiErrorResponse
import com.sadikahmetozdemir.sadik_fodamy.api.ApiResponse
import com.sadikahmetozdemir.sadik_fodamy.api.ApiSuccessResponse
import com.sadikahmetozdemir.sadik_fodamy.api.LoginAPI
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.*
import com.sadikahmetozdemir.sadik_fodamy.utils.NETWORK_ERROR_MESSAGE
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject


class AuthRepository @Inject constructor(private val loginAPI: LoginAPI) {


    suspend fun loginRequest(loginRequestModel: LoginRequestModel): Resource<LoginResponseModel>? {
        return try {
            val response = loginAPI.loginRequest(loginRequestModel)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success(apiResponse.body)
                }
                is ApiErrorResponse -> {
                    Resource.error(apiResponse.errorMessage)
                }
                else -> Resource.error(Result())
            }
        } catch (exception: IOException) {
            val apiException = ApiException.create(exception)
            Resource.error(apiException, null)
        }
    }
    suspend fun registerRequest(registerRequestModel: RegisterRequestModel):Resource<RegisterResponseModel>{
        return try {
            val response=loginAPI.registerRequest(registerRequestModel)
            when(val apiResponse=ApiResponse.create(response)){
                is ApiSuccessResponse->{
                    Resource.success((apiResponse.body))
                }
                is ApiErrorResponse ->{
                    Resource.error(apiResponse.errorMessage)
                }
                else -> Resource.error(Result())


            }
        }catch (exception:IOException){
            val apiException=ApiException.create(exception)
            Resource.error(apiException,null)
        }
    }
}


class ApiException {

    companion object {
        fun create(error: Exception): Result = NETWORK_ERROR_MESSAGE
    }
}