package com.sadikahmetozdemir.data.shared.remote

import com.sadikahmetozdemir.data.shared.local.PaginationModel


data class FavoritesResponseModel(
    var data: List<FavoritesCategoryModel>,
    var pagination: PaginationModel
)
