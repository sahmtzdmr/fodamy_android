package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentResponse(
    var data: List<Comment>?,
    var pagination: Pagination
) : Parcelable
