package com.sadikahmetozdemir.data.shared.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class TimeOfRecipeModel(var id: Int, var text: String) : Parcelable
