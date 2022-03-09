package com.sadikahmetozdemir.data.shared.repositories

import androidx.paging.PagingData
import com.sadikahmetozdemir.data.mappers.toDomainModel
import com.sadikahmetozdemir.data.shared.local.converters.fromJson
import com.sadikahmetozdemir.data.shared.utils.AUTH_LOGIN_SUCCESS
import com.sadikahmetozdemir.data.utils.JsonReader
import com.sadikahmetozdemir.domain.entities.Auth
import com.sadikahmetozdemir.domain.entities.BaseModel
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.entities.LoginRequest
import com.sadikahmetozdemir.domain.entities.LoginResponseModel
import com.sadikahmetozdemir.domain.entities.Logout
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.entities.RegisterRequest
import com.sadikahmetozdemir.domain.entities.UserProfile
import com.sadikahmetozdemir.domain.repositories.AuthRepository
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MockRepository @Inject constructor(private val jsonReader: JsonReader) :
    AuthRepository,
    FeedRepository,
    UserRepository, BaseRepository() {
    override suspend fun loginRequest(loginRequest: LoginRequest): LoginResponseModel {
        return execute {
            val jsonData = jsonReader.readJson(AUTH_LOGIN_SUCCESS)
             fromJson<com.sadikahmetozdemir.data.shared.remote.LoginResponseModel>(jsonData).toDomainModel() }
    }

    override suspend fun registerRequest(registerRequest: RegisterRequest): Auth {
        TODO("Not yet implemented")
    }

    override suspend fun logoutRequest(): Logout {
        TODO("Not yet implemented")
    }

    override suspend fun feedRequest(page: Int): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun lastAddedRequest(page: Int): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeDetail(recipeID: Int): Recipe {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeDetailComment(recipeID: Int): Comment {
        TODO("Not yet implemented")
    }

    override suspend fun favoriteRecipesRequest(page: Int): List<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun favoriteCategoriesRequest(categoryID: Int, page: Int): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun userRecipeLikeRequest(recipeID: Int): BaseModel {
        TODO("Not yet implemented")
    }

    override suspend fun userRecipeDislikeRequest(recipeID: Int): BaseModel {
        TODO("Not yet implemented")
    }

    override suspend fun userFollowRequest(followedID: Int): BaseModel {
        TODO("Not yet implemented")
    }

    override suspend fun userUnfollowRequest(followedID: Int): BaseModel {
        TODO("Not yet implemented")
    }

    override suspend fun recipeCommentsRequest(categoryID: Int, page: Int): List<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun postRecipeCommentRequest(recipeID: Int, text: String): Comment {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecipeComment(recipeID: Int, commentID: Int): BaseModel {
        TODO("Not yet implemented")
    }

    override suspend fun editRecipeComment(recipeID: Int, commentID: Int, text: String): BaseModel {
        TODO("Not yet implemented")
    }

    override suspend fun getLastEditFromMediator(): Flow<PagingData<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getEditorChoicesFromMediator(): Flow<PagingData<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeCommentFromMediator(recipeID: Int): Flow<PagingData<Comment>> {
        TODO("Not yet implemented")
    }

    override suspend fun userProfileRequest(userID: Int): UserProfile {
        TODO("Not yet implemented")
    }

    override suspend fun userLikesRequest(userID: Int, page: Int): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun userProfileRecipes(userID: Int, page: Int): List<Recipe> {
        TODO("Not yet implemented")
    }


}