package com.sadikahmetozdemir.domain.repositories

import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import androidx.paging.PagingData
import com.sadikahmetozdemir.data.shared.remote.BaseModel
import com.sadikahmetozdemir.data.shared.remote.CommentResponseModel
import com.sadikahmetozdemir.data.shared.remote.FavoritesCategoryModel
import com.sadikahmetozdemir.data.shared.remote.Resource
import kotlinx.coroutines.flow.Flow


interface FeedRepository {

    fun feedRequest(): Flow<PagingData<EditorChoiceModel>>
    fun lastAddedRequest(): Flow<PagingData<EditorChoiceModel>>
    suspend fun getRecipeDetail(recipeID: Int): Resource<EditorChoiceModel>
    suspend fun getRecipeDetailComment(recipeID: Int): Resource<CommentResponseModel>
    fun favoriteRecipesRequest(): Flow<PagingData<FavoritesCategoryModel>>
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