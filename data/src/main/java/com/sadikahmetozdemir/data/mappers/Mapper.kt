package com.sadikahmetozdemir.data.mappers

import com.sadikahmetozdemir.data.shared.local.ImagesModel
import com.sadikahmetozdemir.data.shared.local.NumberOfPersonModel
import com.sadikahmetozdemir.data.shared.local.PaginationModel
import com.sadikahmetozdemir.data.shared.local.TimeOfRecipeModel
import com.sadikahmetozdemir.data.shared.local.User
import com.sadikahmetozdemir.data.shared.local.UserImageModel
import com.sadikahmetozdemir.data.shared.remote.BaseModel
import com.sadikahmetozdemir.data.shared.remote.CategoryModel
import com.sadikahmetozdemir.data.shared.remote.CommentModel
import com.sadikahmetozdemir.data.shared.remote.CommentResponseModel
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.data.shared.remote.LoginRequestModel
import com.sadikahmetozdemir.data.shared.remote.LoginResponseModel
import com.sadikahmetozdemir.data.shared.remote.LogoutModel
import com.sadikahmetozdemir.data.shared.remote.RegisterRequestModel
import com.sadikahmetozdemir.data.shared.remote.RegisterResponseModel
import com.sadikahmetozdemir.data.shared.remote.UserProfileModel
import com.sadikahmetozdemir.domain.entities.CommentResponse
import com.sadikahmetozdemir.domain.entities.Images
import com.sadikahmetozdemir.domain.entities.LoginRequest
import com.sadikahmetozdemir.domain.entities.Logout
import com.sadikahmetozdemir.domain.entities.Pagination
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.entities.RegisterRequest
import com.sadikahmetozdemir.domain.entities.UserProfile


fun User.toDomainModel(): com.sadikahmetozdemir.domain.entities.User =
    com.sadikahmetozdemir.domain.entities.User(
        id = this.id ?: 0,
        username = this.username ?: "",
        image = this.image?.toDomainModel(),
        favoritesCount = this.favoritesCount ?: 0,
        followedCount = this.followedCount ?: 0,
        followingCount = this.followingCount ?: 0,
        isFollowing = this.isFollowing,
        likeCount = this.likesCount ?: 0,
        name = this.name ?: "",
        recipeCount = this.recipeCount ?: 0
    )

fun LoginResponseModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.LoginResponseModel =
    com.sadikahmetozdemir.domain.entities.LoginResponseModel(
        token = this.token,
        user = this.user?.toDomainModel()
    )

fun ImagesModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.Images =
    com.sadikahmetozdemir.domain.entities.Images(
        height = this.height,
        url = this.url,
        width = this.width

    )

fun CommentModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.Comment =
    com.sadikahmetozdemir.domain.entities.Comment(
        id = this.id ?: 0,
        text = this.text ?: "",
        user = this.user?.toDomainModel(),
        difference = this.difference ?: ""

    )

fun RegisterResponseModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.Auth =
    com.sadikahmetozdemir.domain.entities.Auth(
        token = this.token ?: "",
        user = this.user?.toDomainModel()

    )

fun NumberOfPersonModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.NumberOfPerson =
    com.sadikahmetozdemir.domain.entities.NumberOfPerson(
        id = this.id,
        text = this.text
    )

fun TimeOfRecipeModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.TimeOfRecipe =
    com.sadikahmetozdemir.domain.entities.TimeOfRecipe(
        id = this.id,
        text = this.text
    )

fun CategoryModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.Category =
    com.sadikahmetozdemir.domain.entities.Category(
        id = this.id,
        name = this.name,
        image = this.image?.toDomainModel(),
        recipes = this.recipes?.map { it.toDomaninModel() }


    )

fun EditorChoiceModel.toDomaninModel(): Recipe =
    Recipe(
        id = this.id,
        title = this.title ?: "",
        definition = this.definition ?: "",
        ingredients = this.ingredients ?: "",
        directions = this.directions ?: "",
        difference = this.difference ?: "",
        isEditorChoice = this.isEditorChoice,
        haveLiked = this.haveLiked,
        likeCount = this.likeCount ?: 0,
        commentCount = this.commentCount ?: 0,
        user = this.user?.toDomainModel(),
        timeOfRecipe = this.timeOfRecipe?.toDomainModel(),
        numberOfPerson = this.numberOfPerson?.toDomainModel(),
        images = this.images?.map { it.toDomainModel() },
        categoryModel = this.categoryModel?.toDomainModel()
    )

fun BaseModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.BaseModel =
    com.sadikahmetozdemir.domain.entities.BaseModel(
        code = this.code ?: "",
        message = this.message ?: "",
        error = this.error ?: ""
    )

fun LoginRequestModel.toDomainModel(): LoginRequest =
    LoginRequest(
        username = this.username ?: "",
        password = this.password ?: ""
    )

fun RegisterRequestModel.toDomainModel(): RegisterRequest =
    RegisterRequest(
        email = this.email,
        password = this.password,
        username = this.username
    )

fun LogoutModel.toDomainModel(): Logout =
    Logout(
        code = this.code ?: "",
        message = this.message ?: "",
        error = this.error ?: ""
    )

fun PaginationModel.toDomainModel(): Pagination =
    Pagination(
        total = this.total ?: 0,
        perPage = this.perPage ?: 0,
        currentPage = this.currentPage ?: 0,
        lastPage = this.lastPage ?: 0,
        firstItem = this.firstItem ?: 0,
        lastItem = this.lastItem ?: 0

    )

fun CommentResponseModel.toDomainModel(): CommentResponse =
    CommentResponse(
        data = this.data?.map { it.toDomainModel() },
        pagination = this.pagination?.toDomainModel()
    )

fun UserImageModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.Images =
    com.sadikahmetozdemir.domain.entities.Images(
        height = this.height,
        url = this.url,
        width = this.width

    )


fun UserProfileModel.toDomainModel(): UserProfile =
    UserProfile(
        favoritesCount = this.favoritesCount,
        followedCount = this.followedCount,
        followingCount = this.followingCount,
        image = this.image?.toDomainModel(),
        id = this.id,
        isFollowing = this.isFollowing,
        isTopUserChoice = this.isTopUserChoice,
        likesCount = this.likesCount,
        recipeCount = this.recipeCount,
        username = this.username
    )


