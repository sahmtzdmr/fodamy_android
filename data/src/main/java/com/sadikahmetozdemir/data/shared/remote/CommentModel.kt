package com.sadikahmetozdemir.data.shared.remote

import android.os.Parcelable
import com.sadikahmetozdemir.data.shared.local.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentModel(
    var id: Int?,
    var text: String?,
    var difference: String?,
    var user: User?,
):Parcelable
