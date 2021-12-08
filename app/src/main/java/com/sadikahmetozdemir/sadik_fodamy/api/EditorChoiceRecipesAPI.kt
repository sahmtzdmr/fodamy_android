package com.sadikahmetozdemir.sadik_fodamy.api

import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiseResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EditorChoiceRecipesAPI {

    @GET("api/editor-choices")
    suspend fun editorChoicesRecipesRequest(@Query ("page")pageInt: Int): EditorChoiseResponseModel

    @GET("api/recipe/")
    suspend fun lastAddedRecipesRequest(@Query("page") pageInt: Int): EditorChoiseResponseModel


    @GET("api/recipe/{recipe_id}" )
    suspend fun recipeDetailsRequest(@Path ("recipe_id")recipeID: Int):Response<EditorChoiceModel>

    @GET("api/recipe/{recipe_id}/comment" )
    suspend fun recipeDetailsCommentRequest(@Path ("recipe_id")recipeID: Int):Response<EditorChoiceModel>
}