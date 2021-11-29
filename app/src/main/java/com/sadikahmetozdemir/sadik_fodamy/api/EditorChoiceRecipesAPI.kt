package com.sadikahmetozdemir.sadik_fodamy.api

import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiseResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EditorChoiceRecipesAPI {

    @GET("api/editor-choices")
    suspend fun editorChoicesRecipesRequest(@Query ("page")pageInt: Int): EditorChoiseResponseModel
}