package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponseModel(var token: String?, var user: User?) : Parcelable
