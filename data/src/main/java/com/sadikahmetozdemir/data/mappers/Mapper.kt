package com.sadikahmetozdemir.data.mappers

import com.sadikahmetozdemir.data.shared.local.UserModel


fun UserModel.toDomainModel(): com.sadikahmetozdemir.domain.entities.User =
    com.sadikahmetozdemir.domain.entities.User(
        id = this.id,
        username = this.username,
        image = this.image.toDomainModel(),
        favoritesCount = this.favoritesCount!!,
        followedCount = this.followedCount!!,
        followingCount = this.followingCount!!,
        isFollowing = this.isFollowing!!,
        likeCount = this.likesCount!!,
        name = this.name,
        recipeCount = this.recipeCount!!
    )