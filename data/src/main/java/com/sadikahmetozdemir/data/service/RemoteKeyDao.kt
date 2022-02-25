package com.sadikahmetozdemir.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sadikahmetozdemir.data.shared.local.dto.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKey>)

    @Query("select * from remote_keys where keyId=:id")
    suspend fun remoteKeysId(id: Int): RemoteKey?

    @Query("delete from remote_keys")
    suspend fun deleteAll()

}