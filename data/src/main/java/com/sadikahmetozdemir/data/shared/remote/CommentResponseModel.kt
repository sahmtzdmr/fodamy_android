package com.sadikahmetozdemir.data.shared.remote

import android.os.Parcelable
import com.sadikahmetozdemir.data.shared.local.PaginationModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentResponseModel(
    var data: List<CommentModel>,
    var pagination: PaginationModel
):Parcelable
