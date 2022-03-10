package com.sadikahmetozdemir.data.shared.repositories

import androidx.paging.PagingData
import com.sadikahmetozdemir.data.mappers.toDomainModel
import com.sadikahmetozdemir.data.mappers.toDomaninModel
import com.sadikahmetozdemir.data.shared.local.converters.fromJson
import com.sadikahmetozdemir.data.shared.remote.CategoryModel
import com.sadikahmetozdemir.data.shared.remote.CommentModel
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.data.shared.remote.LogoutModel
import com.sadikahmetozdemir.data.shared.remote.RegisterResponseModel
import com.sadikahmetozdemir.data.shared.utils.AUTH_LOGIN_SUCCESS
import com.sadikahmetozdemir.data.shared.utils.AUTH_LOGOUT_ERROR
import com.sadikahmetozdemir.data.shared.utils.AUTH_REGISTER_SUCCESS
import com.sadikahmetozdemir.data.shared.utils.FAVORITES_RECIPE_SUCCESS
import com.sadikahmetozdemir.data.shared.utils.FEED_RECIPE_SUCCESS
import com.sadikahmetozdemir.data.shared.utils.LIKE_REQUEST_SUCCESS
import com.sadikahmetozdemir.data.shared.utils.RECIPE_COMMENTS_SUCCESS
import com.sadikahmetozdemir.data.shared.utils.USER_FOLLOW_SUCCESS
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
            fromJson<com.sadikahmetozdemir.data.shared.remote.LoginResponseModel>(jsonData).toDomainModel()
        }
    }

    override suspend fun registerRequest(registerRequest: RegisterRequest): Auth {
        return execute {
            val jsonData = jsonReader.readJson(AUTH_REGISTER_SUCCESS)
            fromJson<RegisterResponseModel>(jsonData).toDomainModel()
        }
    }

    override suspend fun logoutRequest(): Logout {
        return execute {
            val jsonData = jsonReader.readJson(AUTH_LOGOUT_ERROR)
            fromJson<LogoutModel>(jsonData).toDomainModel()
        }
    }

    override suspend fun feedRequest(page: Int): List<Recipe> {
        return listOf(execute {
            val jsonData = jsonReader.readJson(FEED_RECIPE_SUCCESS)
            fromJson<EditorChoiceModel>(jsonData).toDomaninModel()
        })
    }

    override suspend fun lastAddedRequest(page: Int): List<Recipe> {
        return listOf(execute {
            val jsonData = jsonReader.readJson(FEED_RECIPE_SUCCESS)
            fromJson<EditorChoiceModel>(jsonData).toDomaninModel()
        })
    }

    override suspend fun getRecipeDetail(recipeID: Int): Recipe {
        return execute {
            val jsonData = jsonReader.readJson(FEED_RECIPE_SUCCESS)
            fromJson<EditorChoiceModel>(jsonData).toDomaninModel()
        }
    }

    override suspend fun getRecipeDetailComment(recipeID: Int): Comment {
        return execute {
            val jsonData = jsonReader.readJson(RECIPE_COMMENTS_SUCCESS)
            fromJson<CommentModel>(jsonData).toDomainModel()
        }
    }

    override suspend fun favoriteRecipesRequest(page: Int): List<Category> {
        return listOf(execute {
            val jsonData = jsonReader.readJson(FAVORITES_RECIPE_SUCCESS)
            fromJson<CategoryModel>(jsonData).toDomainModel()
        })
    }

    override suspend fun favoriteCategoriesRequest(categoryID: Int, page: Int): List<Recipe> {
        return listOf(execute {
            val jsonData = jsonReader.readJson(FAVORITES_RECIPE_SUCCESS)
            fromJson<EditorChoiceModel>(jsonData).toDomaninModel()
        })
    }

    override suspend fun userRecipeLikeRequest(recipeID: Int): BaseModel {
        return execute {
            val jsonData = jsonReader.readJson(LIKE_REQUEST_SUCCESS)
            fromJson<com.sadikahmetozdemir.data.shared.remote.BaseModel>(jsonData).toDomainModel()
        }
    }

    override suspend fun userRecipeDislikeRequest(recipeID: Int): BaseModel {
        return execute {
            val jsonData = jsonReader.readJson(LIKE_REQUEST_SUCCESS)
            fromJson<com.sadikahmetozdemir.data.shared.remote.BaseModel>(jsonData).toDomainModel()
        }
    }

    override suspend fun userFollowRequest(followedID: Int): BaseModel {
        return execute {
            val jsonData = jsonReader.readJson(USER_FOLLOW_SUCCESS)
            fromJson<com.sadikahmetozdemir.data.shared.remote.BaseModel>(jsonData).toDomainModel()
        }
    }

    override suspend fun userUnfollowRequest(followedID: Int): BaseModel {
        return execute {
            val jsonData = jsonReader.readJson(USER_FOLLOW_SUCCESS)
            fromJson<com.sadikahmetozdemir.data.shared.remote.BaseModel>(jsonData).toDomainModel()
        }
    }

    override suspend fun recipeCommentsRequest(categoryID: Int, page: Int): List<Comment> {
        return listOf(execute {
            val jsonData = jsonReader.readJson(RECIPE_COMMENTS_SUCCESS)
            fromJson<CommentModel>(jsonData).toDomainModel()
        })
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