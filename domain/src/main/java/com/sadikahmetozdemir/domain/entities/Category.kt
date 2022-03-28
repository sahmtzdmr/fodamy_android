package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int?,
    val name: String?,
    val image: Images?,
    val recipes: List<Recipe>?
):Parcelable {
    override fun toString(): String {
        return name.toString()
    }
}
