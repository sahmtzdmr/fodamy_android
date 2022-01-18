package com.sadikahmetozdemir.data.shared.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class NumberOfPersonModel(
    @SerializedName("number_of_person")
    var id: Int,
    var text: String
) : Parcelable
