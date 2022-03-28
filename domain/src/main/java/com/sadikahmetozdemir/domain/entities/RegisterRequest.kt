package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterRequest(
    var email: String?,
    var password: String?,
    var username: String?,
) : Parcelable
