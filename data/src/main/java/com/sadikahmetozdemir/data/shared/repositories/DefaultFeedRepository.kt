import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sadikahmetozdemir.data.mappers.toDomainModel
import com.sadikahmetozdemir.data.mappers.toDomaninModel
import com.sadikahmetozdemir.data.service.ApiErrorResponse
import com.sadikahmetozdemir.data.service.ApiResponse
import com.sadikahmetozdemir.data.service.ApiSuccessResponse
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.domain.requests.Result
import com.sadikahmetozdemir.domain.requests.Resource
import com.sadikahmetozdemir.data.shared.repositories.ApiException
import com.sadikahmetozdemir.data.shared.repositories.FavoriteCategoriesPagingSource
import com.sadikahmetozdemir.data.shared.repositories.FavoritesPagingSource
import com.sadikahmetozdemir.data.shared.repositories.LastAddedPagingSource
import com.sadikahmetozdemir.data.shared.repositories.RecipeCommentsPagingSource
import com.sadikahmetozdemir.data.shared.repositories.RecipePagingSource
import com.sadikahmetozdemir.domain.entities.BaseModel
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.entities.CommentResponse
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class DefaultFeedRepository @Inject constructor(private val editorChoiceRecipesAPI: EditorChoiceRecipesAPI) :
    FeedRepository {

    override fun feedRequest(): Flow<PagingData<Recipe>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = { RecipePagingSource(editorChoiceRecipesAPI) }
        ).flow
    }

    override fun lastAddedRequest(): Flow<PagingData<Recipe>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = { LastAddedPagingSource(editorChoiceRecipesAPI) }
        ).flow
    }

    override suspend fun getRecipeDetail(recipeID: Int): Resource<Recipe> {
        return try {
            val response = editorChoiceRecipesAPI.recipeDetailsRequest(recipeID)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body).toDomaninModel())
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

    override suspend fun getRecipeDetailComment(recipeID: Int): Resource<Comment> {
        return try {
            val response = editorChoiceRecipesAPI.recipeDetailsCommentRequest(recipeID)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body).toDomainModel().data?.get(0))
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

    override fun favoriteRecipesRequest(): Flow<PagingData<com.sadikahmetozdemir.domain.entities.Category>> {
        return FavoritesPagingSource(editorChoiceRecipesAPI).getPagerFlow()
    }

    override fun favoriteCategoriesRequest(
        categoryID: Int
    ): Flow<PagingData<Recipe>> {
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
                    Resource.success((apiResponse.body).toDomainModel())
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
                    Resource.success((apiResponse.body).toDomainModel())
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
                    Resource.success((apiResponse.body).toDomainModel())
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
                    Resource.success((apiResponse.body).toDomainModel())
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
    ): Flow<PagingData<Comment>> {
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

    override suspend fun postRecipeCommentRequest(
        recipeID: Int,
        text: String
    ): Resource<Comment> {
        return try {
            val response = editorChoiceRecipesAPI.postRecipeComments(recipeID, text)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    Resource.success((apiResponse.body).toDomainModel())
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
                    Resource.success((apiResponse.body).toDomainModel())
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
                    Resource.success((apiResponse.body).toDomainModel())
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