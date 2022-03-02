package com.sadikahmetozdemir.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sadikahmetozdemir.data.shared.local.dto.RemoteKeyDatabase

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEditorChoice(remoteKey: List<RemoteKeyDatabase>)

    @Query("select * from remote_keys where keyId=:id")
    suspend fun remoteKeysEditorChoiceId(id: Int): RemoteKeyDatabase?

    @Query("delete from remote_keys")
    suspend fun deleteEditorChoice()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLastAdded(remoteKey: List<RemoteKeyDatabase>)

    @Query("select * from remote_keys where keyId=:id")
    suspend fun remoteKeysLastAddedId(id: Int): RemoteKeyDatabase?

    @Query("delete from remote_keys")
    suspend fun deleteLastAdded()

}