package com.sadikahmetozdemir.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sadikahmetozdemir.data.shared.local.dto.UserDatabase

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDb: UserDatabase)
}