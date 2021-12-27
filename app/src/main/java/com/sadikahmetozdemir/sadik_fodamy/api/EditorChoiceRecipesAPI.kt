package com.sadikahmetozdemir.sadik_fodamy.api

import com.sadikahmetozdemir.sadik_fodamy.shared.remote.*
import retrofit2.Response
import retrofit2.http.*

interface EditorChoiceRecipesAPI {

    @GET("api/editor-choices")
    suspend fun editorChoicesRecipesRequest(@Query ("page")pageInt: Int): EditorChoiseResponseModel

    @GET("api/recipe/")
    suspend fun lastAddedRecipesRequest(@Query("page") pageInt: Int): EditorChoiseResponseModel


    @GET("api/recipe/{recipe_id}" )
    suspend fun recipeDetailsRequest(@Path ("recipe_id")recipeID: Int):Response<EditorChoiceModel>

    @GET("api/recipe/{recipe_id}/comment" )
    suspend fun recipeDetailsCommentRequest(@Path ("recipe_id")recipeID: Int):Response<CommentResponseModel>

    @GET ("api/category-recipes")

    suspend fun favoriteRecipesRequest(@Query("page")pageInt: Int) :FavoritesResponseModel


    @GET("api/category/{category_id}/recipe")

    suspend fun favoriteCategoriesDetailRequest(@Path("category_id")categoryID:Int,@Query("page")pageInt: Int):EditorChoiseResponseModel

    @POST("api/recipe/{recipe_id}/like")
    suspend fun userRecipeLikeRequest(@Path("recipe_id") recipeID:Int): Response<BaseModel>


    @DELETE("api/recipe/{recipe_id}/like")
    suspend fun userRecipeDislikeRequest(@Path("recipe_id") recipeID: Int) : Response<BaseModel>


}