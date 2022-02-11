package com.sadikahmetozdemir.data.shared.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


class CategoryModel(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
)
