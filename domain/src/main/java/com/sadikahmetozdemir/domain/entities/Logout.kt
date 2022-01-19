package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Logout(var code: String, var message: String, var error: String) : Parcelable
