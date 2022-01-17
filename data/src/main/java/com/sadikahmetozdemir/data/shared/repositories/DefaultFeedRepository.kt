import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sadikahmetozdemir.data.service.ApiErrorResponse
import com.sadikahmetozdemir.data.service.ApiResponse
import com.sadikahmetozdemir.data.service.ApiSuccessResponse
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.data.shared.remote.BaseModel
import com.sadikahmetozdemir.data.shared.remote.CommentResponseModel
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.data.shared.remote.FavoritesCategoryModel
import com.sadikahmetozdemir.data.shared.remote.Resource
import com.sadikahmetozdemir.data.shared.remote.Result
import com.sadikahmetozdemir.data.shared.repositories.ApiException
import com.sadikahmetozdemir.data.shared.repositories.FavoriteCategoriesPagingSource
import com.sadikahmetozdemir.data.shared.repositories.FavoritesPagingSource
import com.sadikahmetozdemir.data.shared.repositories.LastAddedPagingSource
import com.sadikahmetozdemir.data.shared.repositories.RecipeCommentsPagingSource
import com.sadikahmetozdemir.data.shared.repositories.RecipePagingSource
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

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