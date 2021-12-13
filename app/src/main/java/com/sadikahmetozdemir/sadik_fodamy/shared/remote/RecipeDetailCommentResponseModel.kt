package com.sadikahmetozdemir.sadik_fodamy.shared.remote

import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel

data class RecipeDetailCommentResponseModel(
    var id: Int?, var text: String?, var difference: String?, var user: UserModel?,
)