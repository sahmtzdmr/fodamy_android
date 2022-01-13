package com.sadikahmetozdemir.sadik_fodamy.shared.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sadikahmetozdemir.sadik_fodamy.api.ApiErrorResponse
import com.sadikahmetozdemir.sadik_fodamy.api.ApiResponse
import com.sadikahmetozdemir.sadik_fodamy.api.ApiSuccessResponse
import com.sadikahmetozdemir.sadik_fodamy.api.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.BaseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.CommentResponseModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.FavoritesCategoryModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Resource
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.Result
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

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
class DefaultFeedRepository @Inject constructor(private val editorChoiceRecipesAPI: EditorChoiceRecipesAPI):FeedRepository {

    override fun feedRequest(): Flow<PagingData<EditorChoiceModel>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = { RecipePagingSource(editorChoiceRecipesAPI) }
        ).flow
    }

    override fun lastAddedRequest(): Flow<PagingData<EditorChoiceModel>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = { LastAddedPagingSource(editorChoiceRecipesAPI) }
        ).flow
    }
    override suspend fun getRecipeDetail(recipeID: Int): Resource<EditorChoiceModel> {
        return try {
            val response = editorChoiceRecipesAPI.recipeDetailsRequest(recipeID)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body))
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

    override suspend fun getRecipeDetailComment(recipeID: Int): Resource<CommentResponseModel> {
        return try {
            val response = editorChoiceRecipesAPI.recipeDetailsCommentRequest(recipeID)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body))
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

    override fun favoriteRecipesRequest(): Flow<PagingData<FavoritesCategoryModel>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = { FavoritesPagingSource(editorChoiceRecipesAPI) }
        ).flow
    }

    override fun favoriteCategoriesRequest(
        categoryID: Int
    ): Flow<PagingData<EditorChoiceModel>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = {
                FavoriteCategoriesPagingSource(
                    editorChoiceRecipesAPI,
                    categoryID
                )
            }
        ).flow
    }

    override suspend fun userRecipeLikeRequest(recipeID: Int): Resource<BaseModel> {
        return try {
            val response = editorChoiceRecipesAPI.userRecipeLikeRequest(recipeID)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body))
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

    override suspend fun userRecipeDislikeRequest(recipeID: Int): Resource<BaseModel> {
        return try {
            val response = editorChoiceRecipesAPI.userRecipeDislikeRequest(recipeID)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body))
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

    override suspend fun userFollowRequest(followedID: Int): Resource<BaseModel> {
        return try {
            val response = editorChoiceRecipesAPI.userFollowing(followedID)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body))
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

    override suspend fun userUnfollowRequest(followedID: Int): Resource<BaseModel> {
        return try {
            val response = editorChoiceRecipesAPI.userUnfollowing(followedID)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body))
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

    override fun recipeCommentsRequest(
        categoryID: Int
    ): Flow<PagingData<EditorChoiceModel>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = {
                RecipeCommentsPagingSource(
                    editorChoiceRecipesAPI,
                    categoryID
                )
            }
        ).flow
    }

    override suspend fun postRecipeCommentRequest(recipeID: Int, text: String): Resource<EditorChoiceModel> {
        return try {
            val response = editorChoiceRecipesAPI.postRecipeComments(recipeID, text)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body))
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

    override suspend fun deleteRecipeComment(recipeID: Int, commentID: Int): Resource<BaseModel> {
        return try {
            val response = editorChoiceRecipesAPI.deleteRecipeComments(recipeID, commentID)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body))
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

    override suspend fun editRecipeComment(
        recipeID: Int,
        commentID: Int,
        text: String
    ): Resource<BaseModel> {
        return try {
            val response = editorChoiceRecipesAPI.editRecipeComments(recipeID, commentID, text)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body))
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

    companion object {
        private val pageConfig = PagingConfig(24, 100, false)
    }
}
