package com.sadikahmetozdemir.data.service

import androidx.room.Dao
import androidx.room.Insert
import com.sadikahmetozdemir.data.shared.local.dto.UserDatabase

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(userDb: UserDatabase)
}