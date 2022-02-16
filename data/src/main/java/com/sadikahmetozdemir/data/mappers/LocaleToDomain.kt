package com.sadikahmetozdemir.data.mappers

import com.sadikahmetozdemir.data.shared.local.dto.CategoryDatabase
import com.sadikahmetozdemir.data.shared.local.dto.NumberOfPersonDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RecipeDataBase
import com.sadikahmetozdemir.data.shared.local.dto.TimeOfRecipeDatabase
import com.sadikahmetozdemir.data.shared.local.dto.UserDatabase
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.entities.NumberOfPerson
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.entities.TimeOfRecipe
import com.sadikahmetozdemir.domain.entities.User

fun RecipeDataBase.toDomainModel(): Recipe {
    return Recipe(
        id = this.id,
        title = this.title ?: "",
        definition = this.definition,
        ingredients = this.ingredients,
        directions = this.directions,
        difference = this.difference,
        isEditorChoice = this.isEditorChoice,
        haveLiked = this.haveLiked,
        likeCount = this.likeCount,
        commentCount = this.commentCount,
        user = this.user.toDomainModel(),
        timeOfRecipe = this.timeOfRecipe.toDomainModel(),
        numberOfPerson = this.numberOfPerson.toDomainModel(),
        categoryModel = this.category.toDomainModel(),
        images= emptyList(),

    )
}
fun UserDatabase.toDomainModel(): User {
    return User(
        id = this.id,
        image = null,// TODO("")
        name = this.name,
        username = this.username,
        favoritesCount = this.favoritesCount,
        followedCount = this.followedCount,
        followingCount = this.followingCount,
        isFollowing = this.isFollowing,
        likeCount = this.likesCount,
        recipeCount = this.recipeCount
    )
}
fun TimeOfRecipeDatabase.toDomainModel(): TimeOfRecipe {
    return TimeOfRecipe(id, text)
}
fun NumberOfPersonDatabase.toDomainModel(): NumberOfPerson {
    return NumberOfPerson(id, text)
}
fun CategoryDatabase.toDomainModel(): Category {
    return Category(
        id = this.id,
        name = this.name,
        image = null,// TODO(""),
        recipes = this.recipes.map { it.toDomainModel() }
    )
}