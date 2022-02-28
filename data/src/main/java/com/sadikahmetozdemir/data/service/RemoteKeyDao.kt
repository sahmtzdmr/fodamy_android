package com.sadikahmetozdemir.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sadikahmetozdemir.data.shared.local.dto.RemoteKeyDatabase

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeyDatabase>)

    @Query("select * from remote_keys where keyId=:id")
    suspend fun remoteKeysId(id: Int): RemoteKeyDatabase?

    @Query("delete from remote_keys")
    suspend fun deleteAll()

}