package com.sadikahmetozdemir.data.shared.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoryModel(
    var id: Int?,
    var name: String?,
) : Parcelable
