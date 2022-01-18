package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import com.sadikahmetozdemir.data.shared.local.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class Register(
    var token: String, var user: User?
    ) : Parcelable
