package com.sadikahmetozdemir.data.shared.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NumberOfPersonModel(
    var number_of_person: Int?,
    var text: String?
) : Parcelable
