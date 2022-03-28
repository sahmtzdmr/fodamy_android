import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.sadikahmetozdemir.data.mappers.toDomainModel
import com.sadikahmetozdemir.data.mappers.toDomaninModel
import com.sadikahmetozdemir.data.mappers.toLocalDto
import com.sadikahmetozdemir.data.service.RecipesAPI
import com.sadikahmetozdemir.data.service.dao.CommentDao
import com.sadikahmetozdemir.data.service.dao.RecipeDao
import com.sadikahmetozdemir.data.shared.local.database.AppDatabase
import com.sadikahmetozdemir.data.shared.local.dto.remotemediators.CommentRemoteMediator
import com.sadikahmetozdemir.data.shared.local.dto.remotemediators.EditorChoiceRemoteMediator
import com.sadikahmetozdemir.data.shared.local.dto.remotemediators.LastAddedRemoteMediator
import com.sadikahmetozdemir.data.shared.repositories.BaseRepository
import com.sadikahmetozdemir.domain.entities.BaseModel
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.entities.NumberOfPerson
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.entities.TimeOfRecipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@ExperimentalPagingApi
class DefaultFeedRepository @Inject constructor(
    private val recipesAPI: RecipesAPI,
    private val recipeDao: RecipeDao,
    private val commentDao: CommentDao,
    private val appDatabase: AppDatabase
) :
    FeedRepository, BaseRepository() {

    override suspend fun feedRequest(page: Int): List<Recipe> {
        return execute {
            val local =
                fetchFromLocal { recipeDao.getEditorChoicesFromApi().map { it.toDomainModel() } }
            ((if (local?.isNotEmpty() == true) {
                local
            } else {
                val remote = recipesAPI.editorChoicesRecipesRequest(page).data
                saveToLocal { recipeDao.insertRecipes(remote.map { it.toLocalDto(isLastAdded = true) }) }
                remote.map { it.toDomaninModel() }
            }))
        }

    }

    override suspend fun lastAddedRequest(page: Int): List<Recipe> =
        execute {
            val local =
                fetchFromLocal { recipeDao.getLastAddedFromApi().map { it.toDomainModel() } }
            if (local?.isNotEmpty() == true) {
                local
            } else {
                val remote = recipesAPI.lastAddedRecipesRequest(page).data
                saveToLocal { recipeDao.insertRecipes(remote.map { it.toLocalDto(isLastAdded = true) }) }
                remote.map { it.toDomaninModel() }
            }
        }


    override suspend fun fetchRecipeDetail(recipeID: Int): Recipe {
        return execute {
            val local =
                fetchFromLocal { recipeDao.getRecipeDetails(recipeID).toDomainModel() }
            if (local != null) {
                local
            } else {
                val remote = recipesAPI.recipeDetailsRequest(recipeID)
                remote.toDomaninModel()
            }
        }
    }


    override suspend fun fetchRecipeDetailComment(recipeID: Int): Comment {
        return execute {
            recipesAPI.recipeDetailsCommentRequest(recipeID)
                .toDomainModel().data?.get(0)!!
        }
    }

    override suspend fun favoriteRecipesRequest(page: Int): List<com.sadikahmetozdemir.domain.entities.Category> {
        return execute { recipesAPI.favoriteRecipesRequest(page).data?.map { it.toDomainModel() }!! }
    }

    override suspend fun favoriteCategoriesRequest(
        categoryID: Int, page: Int
    ): List<Recipe> {
        return execute {
            recipesAPI.favoriteCategoriesDetailRequest(
                categoryID,
                page
            ).data.map { it.toDomaninModel() }
        }
    }

    override suspend fun userRecipeLikeRequest(recipeID: Int): BaseModel {
        return execute { recipesAPI.userRecipeLikeRequest(recipeID).toDomainModel() }
    }

    override suspend fun userRecipeDislikeRequest(recipeID: Int): BaseModel {
        return execute { recipesAPI.userRecipeDislikeRequest(recipeID).toDomainModel() }
    }

    override suspend fun userFollowRequest(followedID: Int): BaseModel {
        return execute {
            recipesAPI.userFollowing(followedID).body()?.toDomainModel()!!
        }
    }

    override suspend fun userUnfollowRequest(followedID: Int): BaseModel {
        return execute {
            recipesAPI.userUnfollowing(followedID).body()?.toDomainModel()!!
        }
    }

    override suspend fun recipeCommentsRequest(
        categoryID: Int, page: Int
    ): List<Comment> {
        return execute {
            recipesAPI.getRecipeComments(
                categoryID,
                page
            ).data.map { it.toDomainModel() }
        }
    }

    override suspend fun postRecipeCommentRequest(
        recipeID: Int,
        text: String
    ): Comment {
        return execute {
            recipesAPI.postRecipeComments(recipeID, text).body()?.toDomainModel()!!
        }
    }

    override suspend fun deleteRecipeComment(recipeID: Int, commentID: Int): BaseModel {
        return execute {
            recipesAPI.deleteRecipeComments(recipeID, commentID).body()
                ?.toDomainModel()!!
        }
    }

    override suspend fun editRecipeComment(
        recipeID: Int,
        commentID: Int,
        text: String
    ): BaseModel {
        return execute {
            recipesAPI.editRecipeComments(recipeID, commentID, text).body()
                ?.toDomainModel()!!
        }
    }

    override suspend fun getLastEditFromMediator(): Flow<PagingData<Recipe>> {
        return execute {
            val pagingSourceFactory = { recipeDao.getLastAdded() }
            Pager(
                config = PAGE_CONFIG,
                remoteMediator = LastAddedRemoteMediator(recipesAPI, appDatabase),
                pagingSourceFactory = pagingSourceFactory
            ).flow.map { pagingData ->
                pagingData.map {
                    it.toDomainModel()
                }
            }

        }

    }


    override suspend fun getEditorChoicesFromMediator(): Flow<PagingData<Recipe>> {
        return execute {
            val pagingSourceFactory = { recipeDao.getEditorChoices() }
            Pager(
                config = PAGE_CONFIG,
                remoteMediator = EditorChoiceRemoteMediator(recipesAPI, appDatabase),
                pagingSourceFactory = pagingSourceFactory
            ).flow.map { pagingData ->
                pagingData.map {
                    it.toDomainModel()
                }
            }

        }

    }

    override suspend fun getRecipeCommentFromMediator(recipeID: Int): Flow<PagingData<Comment>> {
        return execute {
            val pagingSourceFactory = { commentDao.getRecipeComments(recipeID) }
            Pager(
                config = PAGE_CONFIG,
                remoteMediator = CommentRemoteMediator(recipesAPI, appDatabase, recipeID),
                pagingSourceFactory = pagingSourceFactory
            ).flow.map { pagingData ->
                pagingData.map { it.toDomainModel() }
            }
        }
    }

    override suspend fun getRecipeServing(): List<NumberOfPerson> {
        return execute {
            recipesAPI.getRecipeServing().data.map { it.toDomainModel() }
        }
    }

    override suspend fun getRecipeTime(): List<TimeOfRecipe> {
        return execute {
            recipesAPI.getRecipeTimes().data.map { it.toDomainModel() }
        }
    }

    override suspend fun getRecipeCategory(): List<Category> {
        return (execute {
            recipesAPI.getRecipeCategory().data.map { it.toDomainModel() }

        })
    }

    override suspend fun postNewRecipeRequest(
        title: String,
        ingredients: String,
        direction: String,
        categoryID: Int,
        numberOfPersonID: Int,
        timeOfRecipeID: Int,
        image: File
    ): Recipe {
        return execute {
            recipesAPI.postNewRecipe(
                title = title,
                ingredients = ingredients,
                direction = direction,
                categoryID = categoryID,
                numberOfPersonID = numberOfPersonID,
                timeOfRecipeID = timeOfRecipeID,
                images = getMultipartFiles(image)
            ).toDomaninModel()
        }
    }

    private fun getMultipartFiles(source: File): MultipartBody.Part {
        val reqFile =
            source.asRequestBody("file".toMediaTypeOrNull())
        val part =
            MultipartBody.Part.createFormData(
                "images[0]",
                source.name,
                reqFile
            )
        return part
    }


    companion object {
        private val PAGE_CONFIG =
            PagingConfig(maxSize = 100, pageSize = 24, enablePlaceholders = false)
    }
}