package com.sadikahmetozdemir.data.mappers

import com.sadikahmetozdemir.data.shared.local.dto.CategoryDatabase
import com.sadikahmetozdemir.data.shared.local.dto.CommentDatabase
import com.sadikahmetozdemir.data.shared.local.dto.ImageDatabase
import com.sadikahmetozdemir.data.shared.local.dto.NumberOfPersonDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RecipeDatabase
import com.sadikahmetozdemir.data.shared.local.dto.TimeOfRecipeDatabase
import com.sadikahmetozdemir.data.shared.local.dto.UserDatabase
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.domain.entities.Images
import com.sadikahmetozdemir.domain.entities.NumberOfPerson
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.entities.TimeOfRecipe
import com.sadikahmetozdemir.domain.entities.User

fun RecipeDatabase.toDomainModel(): Recipe {
    return Recipe(
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
        categoryModel = this.category?.toDomainModel(),
        images = this.image?.map { it.toDomainModel() },

        )
}

fun CommentDatabase.toDomainModel(): Comment {
    return Comment(
        id = this.id,
        difference = this.difference ?: "",
        text = this.text ?: "",
        user = this.user?.toDomainModel()
    )
}

fun UserDatabase.toDomainModel(): User {
    return User(
        id = this.id,
        image = this.image?.toDomainModel(),
        name = this.name ?: "",
        username = this.username ?: "",
        favoritesCount = this.favoritesCount ?: 0,
        followedCount = this.followedCount ?: 0,
        followingCount = this.followingCount ?: 0,
        isFollowing = this.isFollowing,
        likeCount = this.likesCount ?: 0,
        recipeCount = this.recipeCount ?: 0
    )
}

fun TimeOfRecipeDatabase.toDomainModel(): TimeOfRecipe {
    return TimeOfRecipe(
        id,
        text ?: ""
    )
}

fun NumberOfPersonDatabase.toDomainModel(): NumberOfPerson {
    return NumberOfPerson(
        id,
        text ?: ""
    )
}

fun ImageDatabase.toDomainModel(): Images {
    return Images(
        height = this.height ?: 0,
        width = this.width ?: 0,
        url = this.url ?: "",
    )
}

fun CategoryDatabase.toDomainModel(): Category {
    return Category(
        id = this.id,
        name = this.name ?: "",
        image = this.image?.toDomainModel(),
        recipes = this.recipes?.map { it.toDomainModel() }
    )
}