package com.sadikahmetozdemir.data.shared.local.converters

import androidx.room.TypeConverter
import com.sadikahmetozdemir.data.shared.local.dto.UserDatabase

class UserConverter {
    @TypeConverter
    fun userToJson(user: UserDatabase): String {
        return toJson<UserDatabase>(user)
    }

    @TypeConverter
    fun jsonToUser(user: String): UserDatabase {
        return fromJson<UserDatabase>(user)
    }
}