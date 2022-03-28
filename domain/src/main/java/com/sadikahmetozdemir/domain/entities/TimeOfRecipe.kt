package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimeOfRecipe(
    val id: Int?,
    val text: String?
) : Parcelable {
    override fun toString(): String {
        return text.toString() + " dk"
    }
}
