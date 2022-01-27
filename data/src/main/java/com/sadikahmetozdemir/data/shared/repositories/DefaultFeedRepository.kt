import androidx.paging.PagingConfig
import com.sadikahmetozdemir.data.mappers.toDomainModel
import com.sadikahmetozdemir.data.mappers.toDomaninModel
import com.sadikahmetozdemir.data.service.ApiErrorResponse
import com.sadikahmetozdemir.data.service.ApiResponse
import com.sadikahmetozdemir.data.service.ApiSuccessResponse
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.domain.requests.Result
import com.sadikahmetozdemir.domain.requests.Resource
import com.sadikahmetozdemir.data.shared.repositories.ApiException
import com.sadikahmetozdemir.data.shared.repositories.BaseRepository

import com.sadikahmetozdemir.domain.entities.BaseModel
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import java.io.IOException
import javax.inject.Inject

class DefaultFeedRepository @Inject constructor(private val editorChoiceRecipesAPI: EditorChoiceRecipesAPI) :
    FeedRepository, BaseRepository() {

    override suspend fun feedRequest(page: Int): List<Recipe> {
        return execute {
            editorChoiceRecipesAPI.editorChoicesRecipesRequest(page).data?.map { it.toDomaninModel() }!!
        }

    }

    override suspend fun lastAddedRequest(page: Int): List<Recipe> {
        return execute { editorChoiceRecipesAPI.lastAddedRecipesRequest(page).data?.map { it.toDomaninModel() }!! }
    }

    override suspend fun getRecipeDetail(recipeID: Int): Recipe {
        return execute {
            editorChoiceRecipesAPI.recipeDetailsRequest(recipeID).toDomaninModel()

        }
    }


    override suspend fun getRecipeDetailComment(recipeID: Int): Comment {
        return execute {
            editorChoiceRecipesAPI.recipeDetailsCommentRequest(recipeID).body()
                ?.toDomainModel()?.data?.get(0)!!
        }
    }

    override suspend fun favoriteRecipesRequest(page: Int): List<com.sadikahmetozdemir.domain.entities.Category> {
        return execute { editorChoiceRecipesAPI.favoriteRecipesRequest(page).data?.map { it.toDomainModel() }!! }
    }

    override suspend fun favoriteCategoriesRequest(
        categoryID: Int, page: Int
    ): List<Recipe> {
        return execute {
            editorChoiceRecipesAPI.favoriteCategoriesDetailRequest(
                categoryID,
                page
            ).data?.map { it.toDomaninModel() }!!
        }
    }

    override suspend fun userRecipeLikeRequest(recipeID: Int): BaseModel {
     return execute { editorChoiceRecipesAPI.userRecipeLikeRequest(recipeID).body()?.toDomainModel()!! }
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

    override suspend fun recipeCommentsRequest(
        categoryID: Int, page: Int
    ): List<Comment> {
        return editorChoiceRecipesAPI.getRecipeComments(
            categoryID,
            page
        ).data?.map { it.toDomainModel() }!!

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