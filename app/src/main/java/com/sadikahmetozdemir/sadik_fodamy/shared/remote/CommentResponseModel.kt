package com.sadikahmetozdemir.sadik_fodamy.shared.remote

import android.os.Parcelable
import com.sadikahmetozdemir.sadik_fodamy.shared.local.PaginationModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentResponseModel(
    var data: List<RecipeDetailCommentResponseModel>,
    var pagination: PaginationModel
):Parcelable
