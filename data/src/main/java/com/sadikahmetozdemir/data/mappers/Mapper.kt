package com.sadikahmetozdemir.data.mappers

import com.sadikahmetozdemir.data.shared.local.ImagesModel
import com.sadikahmetozdemir.data.shared.local.NumberOfPersonModel
import com.sadikahmetozdemir.data.shared.local.TimeOfRecipeModel
import com.sadikahmetozdemir.data.shared.local.User
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.data.shared.remote.Category
import com.sadikahmetozdemir.data.shared.remote.CommentModel
import com.sadikahmetozdemir.data.shared.remote.LoginResponseModel


fun User.toDomainModel(): com.sadikahmetozdemir.domain.entities.User =
    com.sadikahmetozdemir.domain.entities.User(
        id = this.id,
        username = this.username,
        image = this.image.toDomainModel(),
        favoritesCount = this.favoritesCount,
        followedCount = this.followedCount,
        followingCount = this.followingCount,
        isFollowing = this.isFollowing,
        likeCount = this.likesCount,
        name = this.name,
        recipeCount = this.recipeCount
    )

fun LoginResponseModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.LoginResponseModel =
    com.sadikahmetozdemir.domain.entities.LoginResponseModel(
        token = this.token,
        user = this.user.toDomainModel()
    )

fun ImagesModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.Images =
    com.sadikahmetozdemir.domain.entities.Images(
        height = this.height,
        url = this.url,
        width = this.width

    )

fun CommentModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.Comment =
    com.sadikahmetozdemir.domain.entities.Comment(
        id = this.id,
        text = this.text,
        user = this.user?.toDomainModel(),
        difference = this.difference

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

fun Category.toDomainModel(): com.sadikahmetozdemir.domain.entities.Category =
    com.sadikahmetozdemir.domain.entities.Category(
        id = this.id,
        name=this.name,
        image = this.image?.toDomainModel(),
        recipes = this.recipes.map { it.toDomaninModel() }


    )

fun EditorChoiceModel.toDomaninModel(): com.sadikahmetozdemir.domain.entities.Recipe =
    com.sadikahmetozdemir.domain.entities.Recipe(
        id = this.id,
        title = this.title,
        definition = this.definition,
        ingredients=this.ingredients,
        directions=this.directions,
        difference=this.difference,
        isEditorChoice=this.isEditorChoice,
        isLiked=this.isLiked,
        likeCount = this.likeCount.toString(),
        numberOfFavoriteCount = this.numberOfFavoriteCount.toString(),
        commentCount = this.commentCount.toString(),
        category = this.categoryModel.toDomainModel(),
        user = this.user.toDomainModel(),
        timeOfRecipe = this.timeOfRecipe.toDomainModel(),
        numberOfPerson = this.numberOfPerson.toDomainModel(),
        images = this.images?.map { it.toDomainModel() }


    )

