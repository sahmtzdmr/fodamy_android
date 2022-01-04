package com.sadikahmetozdemir.sadik_fodamy.api
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.BaseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.CommentResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiseResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.FavoritesResponseModel
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface EditorChoiceRecipesAPI {

    @GET("api/editor-choices")
    suspend fun editorChoicesRecipesRequest(@Query("page")pageInt: Int): EditorChoiseResponseModel

    @GET("api/recipe/")
    suspend fun lastAddedRecipesRequest(@Query("page") pageInt: Int): EditorChoiseResponseModel

    @GET("api/recipe/{recipe_id}")
    suspend fun recipeDetailsRequest(@Path("recipe_id")recipeID: Int): Response<EditorChoiceModel>

    @GET("api/recipe/{recipe_id}/comment")
    suspend fun recipeDetailsCommentRequest(@Path("recipe_id")recipeID: Int): Response<CommentResponseModel>

    @GET("api/category-recipes")

    suspend fun favoriteRecipesRequest(@Query("page")pageInt: Int): FavoritesResponseModel

    @GET("api/category/{category_id}/recipe")

    suspend fun favoriteCategoriesDetailRequest(@Path("category_id")categoryID: Int, @Query("page")pageInt: Int): EditorChoiseResponseModel

    @POST("api/recipe/{recipe_id}/like")
    suspend fun userRecipeLikeRequest(@Path("recipe_id") recipeID: Int): Response<BaseModel>

    @DELETE("api/recipe/{recipe_id}/like")
    suspend fun userRecipeDislikeRequest(@Path("recipe_id") recipeID: Int): Response<BaseModel>

    @POST("api/user/{followedId}/following")
    suspend fun userFollowing(@Path("followedId") followedID: Int): Response<BaseModel>

    @DELETE("api/user/{followedId}/following")
    suspend fun userUnfollowing(@Path("followedId") followedID: Int): Response<BaseModel>

    @GET("api/recipe/{recipe_id}/comment")
    suspend fun getRecipeComments(@Path("recipe_id") recipeID: Int, @Query("page") pageInt: Int): EditorChoiseResponseModel

    @POST("api/recipe/{recipe_id}/comment")
    suspend fun postRecipeComments(@Path("recipe_id") recipeID: Int, @Query("text") text: String): Response<EditorChoiceModel>

    @DELETE ("api/recipe/{recipe_id}/comment/{comment_id}")
    suspend fun deleteRecipeComments(@Path("recipe_id") recipeID: Int,@Path("comment_id") commentID:Int): Response<BaseModel>
}
