package com.sadikahmetozdemir.sadik_fodamy.shared.repositories

import com.sadikahmetozdemir.sadik_fodamy.api.ApiErrorResponse
import com.sadikahmetozdemir.sadik_fodamy.api.ApiResponse
import com.sadikahmetozdemir.sadik_fodamy.api.ApiSuccessResponse
import com.sadikahmetozdemir.sadik_fodamy.api.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.*
import java.io.IOException
import javax.inject.Inject

class FeedRepository @Inject constructor(private val editorChoiceRecipesAPI: EditorChoiceRecipesAPI) {

    suspend fun feedRequest(): Resource<EditorChoiseResponseModel>? {
        return try {
            val response = editorChoiceRecipesAPI.editorChoicesRecipesRequest()
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
}