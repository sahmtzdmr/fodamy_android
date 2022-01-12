package com.sadikahmetozdemir.sadik_fodamy.shared.remote

import android.os.Parcelable
import com.sadikahmetozdemir.sadik_fodamy.shared.local.UserModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeDetailCommentResponseModel(
    var id: Int?,
    var text: String?,
    var difference: String?,
    var user: UserModel?,
):Parcelable
