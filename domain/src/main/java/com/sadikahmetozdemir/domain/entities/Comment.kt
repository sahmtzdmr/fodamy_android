package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment(
    val id: Int,
    val text: String,
    val user: User?,
    val difference: String

):Parcelable
