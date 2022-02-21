import com.sadikahmetozdemir.data.mappers.toDomainModel
import com.sadikahmetozdemir.data.mappers.toDomaninModel
import com.sadikahmetozdemir.data.mappers.toLocalDto
import com.sadikahmetozdemir.data.service.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.data.service.RecipeDao
import com.sadikahmetozdemir.data.shared.repositories.BaseRepository
import com.sadikahmetozdemir.domain.entities.BaseModel
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import javax.inject.Inject

class DefaultFeedRepository @Inject constructor(
    private val editorChoiceRecipesAPI: EditorChoiceRecipesAPI,
    private val recipeDao: RecipeDao
) :
    FeedRepository, BaseRepository() {

    override suspend fun feedRequest(page: Int): List<Recipe> {
        return execute {
           try{
               val data = editorChoiceRecipesAPI.editorChoicesRecipesRequest(page).data
               recipeDao.insertRecipes(data?.map { it.toLocalDto() }!!)
               data.map { it.toDomaninModel() }
           }
           catch (ex:Exception)
           {
               throw ex
           }
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
            editorChoiceRecipesAPI.recipeDetailsCommentRequest(recipeID)
                .toDomainModel().data?.get(0)!!
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
        return execute { editorChoiceRecipesAPI.userRecipeLikeRequest(recipeID).toDomainModel() }
    }

    override suspend fun userRecipeDislikeRequest(recipeID: Int): BaseModel {
        return execute { editorChoiceRecipesAPI.userRecipeDislikeRequest(recipeID).toDomainModel() }
    }

    override suspend fun userFollowRequest(followedID: Int): BaseModel {
        return execute {
            editorChoiceRecipesAPI.userFollowing(followedID).body()?.toDomainModel()!!
        }
    }

    override suspend fun userUnfollowRequest(followedID: Int): BaseModel {
        return execute {
            editorChoiceRecipesAPI.userUnfollowing(followedID).body()?.toDomainModel()!!
        }
    }

    override suspend fun recipeCommentsRequest(
        categoryID: Int, page: Int
    ): List<Comment> {
        return execute {
            editorChoiceRecipesAPI.getRecipeComments(
                categoryID,
                page
            ).data?.map { it.toDomainModel() }!!
        }
    }

    override suspend fun postRecipeCommentRequest(
        recipeID: Int,
        text: String
    ): Comment {
        return execute {
            editorChoiceRecipesAPI.postRecipeComments(recipeID, text).body()?.toDomainModel()!!
        }
    }

    override suspend fun deleteRecipeComment(recipeID: Int, commentID: Int): BaseModel {
        return execute {
            editorChoiceRecipesAPI.deleteRecipeComments(recipeID, commentID).body()
                ?.toDomainModel()!!
        }
    }

    override suspend fun editRecipeComment(
        recipeID: Int,
        commentID: Int,
        text: String
    ): BaseModel {
        return execute {
            editorChoiceRecipesAPI.editRecipeComments(recipeID, commentID, text).body()
                ?.toDomainModel()!!
        }
    }
}