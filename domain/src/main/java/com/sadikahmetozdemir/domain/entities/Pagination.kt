package com.sadikahmetozdemir.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pagination(
    var total: Int?,
    var perPage: Int?,
    var currentPage: Int?,
    var lastPage: Int?,
    var firstItem: Int?,
    var lastItem: Int?
) : Parcelable
