package com.sadikahmetozdemir.domain.repositories

import androidx.paging.PagingData
import com.sadikahmetozdemir.domain.entities.BaseModel
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.entities.CommentResponse
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.requests.Resource
import kotlinx.coroutines.flow.Flow


interface FeedRepository {

    suspend fun feedRequest(page: Int = 1): List<Recipe>
    suspend fun lastAddedRequest(page: Int=1): List<Recipe>
    suspend fun getRecipeDetail(recipeID: Int): Recipe
    suspend fun getRecipeDetailComment(recipeID: Int): Comment
    suspend fun favoriteRecipesRequest(page:Int=1): List<Category>
    suspend fun favoriteCategoriesRequest(categoryID: Int,page: Int = 1): List<Recipe>
    suspend fun userRecipeLikeRequest(recipeID: Int): BaseModel
    suspend fun userRecipeDislikeRequest(recipeID: Int): BaseModel
    suspend fun userFollowRequest(followedID: Int): BaseModel
    suspend fun userUnfollowRequest(followedID: Int): BaseModel
    suspend fun recipeCommentsRequest(categoryID: Int, page: Int = 1): List<Comment>
    suspend fun postRecipeCommentRequest(recipeID: Int, text: String): Comment
    suspend fun deleteRecipeComment(recipeID: Int, commentID: Int): BaseModel
    suspend fun editRecipeComment(recipeID: Int, commentID: Int, text: String): BaseModel
    suspend fun getLastEditFromMediator(): Flow<PagingData<Recipe>>
}