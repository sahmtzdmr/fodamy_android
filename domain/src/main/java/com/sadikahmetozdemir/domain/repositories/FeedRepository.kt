package com.sadikahmetozdemir.domain.repositories

import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import androidx.paging.PagingData
import com.sadikahmetozdemir.data.shared.remote.BaseModel
import com.sadikahmetozdemir.data.shared.remote.CommentResponseModel
import com.sadikahmetozdemir.data.shared.remote.Category
import com.sadikahmetozdemir.data.shared.remote.Resource
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.entities.Recipe
import kotlinx.coroutines.flow.Flow


interface FeedRepository {

    fun feedRequest(): Flow<PagingData<EditorChoiceModel>>
    fun lastAddedRequest(): Flow<PagingData<EditorChoiceModel>>
    suspend fun getRecipeDetail(recipeID: Int): Resource<Recipe>
    suspend fun getRecipeDetailComment(recipeID: Int): Resource<Comment>
    fun favoriteRecipesRequest(): Flow<PagingData<Category>>
    fun favoriteCategoriesRequest(categoryID: Int): Flow<PagingData<EditorChoiceModel>>
    suspend fun userRecipeLikeRequest(recipeID: Int): Resource<BaseModel>
    suspend fun userRecipeDislikeRequest(recipeID: Int): Resource<BaseModel>
    suspend fun userFollowRequest(followedID: Int): Resource<BaseModel>
    suspend fun userUnfollowRequest(followedID: Int): Resource<BaseModel>
    fun recipeCommentsRequest(categoryID: Int): Flow<PagingData<EditorChoiceModel>>
    suspend fun postRecipeCommentRequest(recipeID: Int, text: String): Resource<EditorChoiceModel>
    suspend fun deleteRecipeComment(recipeID: Int, commentID: Int): Resource<BaseModel>
    suspend fun editRecipeComment(recipeID: Int, commentID: Int, text: String): Resource<BaseModel>
}