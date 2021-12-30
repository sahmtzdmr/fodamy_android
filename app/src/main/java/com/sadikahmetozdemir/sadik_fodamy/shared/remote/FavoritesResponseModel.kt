package com.sadikahmetozdemir.sadik_fodamy.shared.remote

import com.sadikahmetozdemir.sadik_fodamy.shared.local.PaginationModel

data class FavoritesResponseModel(
    var data: List<FavoritesCategoryModel>,
    var pagination: PaginationModel
)
