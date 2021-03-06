package com.sadikahmetozdemir.data.mappers

import com.sadikahmetozdemir.data.shared.local.ImagesModel
import com.sadikahmetozdemir.data.shared.local.NumberOfPersonModel
import com.sadikahmetozdemir.data.shared.local.TimeOfRecipeModel
import com.sadikahmetozdemir.data.shared.local.User
import com.sadikahmetozdemir.data.shared.local.dto.CategoryDatabase
import com.sadikahmetozdemir.data.shared.local.dto.CommentDatabase
import com.sadikahmetozdemir.data.shared.local.dto.ImageDatabase
import com.sadikahmetozdemir.data.shared.local.dto.NumberOfPersonDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RecipeDatabase
import com.sadikahmetozdemir.data.shared.local.dto.TimeOfRecipeDatabase
import com.sadikahmetozdemir.data.shared.local.dto.UserDatabase
import com.sadikahmetozdemir.data.shared.remote.CategoryModel
import com.sadikahmetozdemir.data.shared.remote.CommentModel
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel

class RemoteToLocale

fun EditorChoiceModel.toLocalDto(isLastAdded: Boolean = false): RecipeDatabase {
    return RecipeDatabase(
        id = this.id,
        title = this.title,
        definition = this.definition ?: "",
        ingredients = this.ingredients ?: "",
        directions = this.directions ?: "",
        difference = this.difference ?: "",
        isLastAdded = isLastAdded,
        isEditorChoice = this.isEditorChoice ?: false,
        haveLiked = this.haveLiked,
        likeCount = this.likeCount ?: 0,
        commentCount = this.commentCount ?: 0,
        user = this.user?.toLocalDto(),
        timeOfRecipe = this.timeOfRecipe?.toLocalDto(),
        numberOfPerson = this.numberOfPerson?.toLocalDto(),
        category = this.categoryModel?.toLocalDto() ?: CategoryDatabase.empty,
        image = this.images?.map { it.toLocalDto() }

    )
}

fun ImagesModel.toLocalDto(): ImageDatabase {
    return ImageDatabase(
        width = this.width,
        height = this.height,
        url = this.url ?: "",
    )
}

fun CommentModel.toLocalDto(recipeId:Int): CommentDatabase {
    return CommentDatabase(
        difference = this.difference ?: "",
        id = this.id,
        text = this.text,
        user = this.user?.toLocalDto(),
        recipeId = recipeId
    )
}

fun User.toLocalDto(): UserDatabase {
    return UserDatabase(
        id = this.id,
        name = this.name ?: "",
        username = this.username ?: "",
        favoritesCount = this.favoritesCount ?: 0,
        followedCount = this.followedCount ?: 0,
        followingCount = this.followingCount ?: 0,
        isFollowing = this.isFollowing,
        likesCount = this.likesCount ?: 0,
        recipeCount = this.recipeCount ?: 0,
        image = this.image?.toLocalDto() ?: ImageDatabase.empty
    )
}

fun TimeOfRecipeModel.toLocalDto(): TimeOfRecipeDatabase {
    return TimeOfRecipeDatabase(
        id = this.id,
        text = this.text
    )
}

fun NumberOfPersonModel.toLocalDto(): NumberOfPersonDatabase {
    return NumberOfPersonDatabase(
        id = this.id,
        text = this.text
    )
}

fun CategoryModel.toLocalDto(): CategoryDatabase {
    return CategoryDatabase(
        id = this.id ?: 0,
        name = this.name ?: "",
        recipes = this.recipes?.map { it.toLocalDto() } ?: emptyList(),
        image = this.image?.toLocalDto() ?: ImageDatabase.empty
    )
}