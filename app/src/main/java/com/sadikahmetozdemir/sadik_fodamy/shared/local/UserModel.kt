package com.sadikahmetozdemir.sadik_fodamy.shared.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    var id: Int?,
    var email: String?,
    var username: String?,
    var name: String?,
    var surname: String?,
    var is_banned:Int?,
    var is_trusted:Int?,
    var followed_count:Int?,
    var following_count:Int?,
    var recipe_count:Int?,
    var is_following:Boolean?,
    var favorites_count:Int?,
    var likes_count:Int?,
    var image : ImagesModel?



):Parcelable