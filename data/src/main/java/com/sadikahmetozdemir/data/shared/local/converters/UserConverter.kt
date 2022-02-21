package com.sadikahmetozdemir.data.shared.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.sadikahmetozdemir.data.shared.local.dto.UserDatabase
import javax.inject.Inject

@ProvidedTypeConverter
class UserConverter @Inject constructor() {
    @TypeConverter
    fun userToJson(user: UserDatabase): String {
        return toJson<UserDatabase>(user)
    }

    @TypeConverter
    fun jsonToUser(user: String): UserDatabase {
        return fromJson<UserDatabase>(user)
    }
}