package com.sadikahmetozdemir.sadik_fodamy.shared.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoryModel(
    var id: Int?,
    var name: String?,
) : Parcelable
