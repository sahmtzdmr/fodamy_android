package com.sadikahmetozdemir.sadik_fodamy.shared.remote

import com.sadikahmetozdemir.sadik_fodamy.shared.local.PaginationModel

data class CommentResponseModel(
    var data: List<RecipeDetailCommentResponseModel>,
    var pagination: PaginationModel
)
