package com.sadikahmetozdemir.sadik_fodamy.shared.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sadikahmetozdemir.sadik_fodamy.api.*
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.*
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject


class FeedRepository @Inject constructor(private val editorChoiceRecipesAPI: EditorChoiceRecipesAPI) {

    fun feedRequest(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<EditorChoiceModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { RecipePagingSource(editorChoiceRecipesAPI) }
        ).flow
    }

    fun lastAddedRequest(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<EditorChoiceModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { LastAddedPagingSource(editorChoiceRecipesAPI) }
        ).flow
    }

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 24)
    }

    suspend fun getRecipeDetail(recipeID: Int): Resource<EditorChoiceModel> {
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

    suspend fun getRecipeDetailComment(recipeID: Int): Resource<CommentResponseModel> {
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

    fun favoriteRecipesRequest(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<FavoritesCategoryModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { FavoritesPagingSource(editorChoiceRecipesAPI) }
        ).flow
    }

    fun favoriteCategoriesRequest(
        categoryID: Int,
        pagingConfig: PagingConfig = getDefaultPageConfig()
    ): Flow<PagingData<EditorChoiceModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                FavoriteCategoriesPagingSource(
                    editorChoiceRecipesAPI,
                    categoryID
                )
            }
        ).flow
    }

    suspend fun userRecipeLikeRequest(recipeID: Int): Resource<BaseModel> {
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

    suspend fun userRecipeDislikeRequest(recipeID: Int): Resource<BaseModel> {
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

    suspend fun userFollowRequest(followedID: Int): Resource<BaseModel> {
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
    suspend fun userUnfollowRequest(followedID: Int): Resource<BaseModel> {
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
}
