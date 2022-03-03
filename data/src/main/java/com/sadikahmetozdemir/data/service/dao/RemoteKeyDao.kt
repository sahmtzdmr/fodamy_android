package com.sadikahmetozdemir.data.service.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sadikahmetozdemir.data.shared.local.dto.RemoteKeyCommentDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RemoteKeyEditorChoiceDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RemoteKeyLastAddedDatabase

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEditorChoice(remoteKeyEditorChoice: List<RemoteKeyEditorChoiceDatabase>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(remoteKeyComment: List<RemoteKeyCommentDatabase>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLastAdded(remoteKeyLastAdded: List<RemoteKeyLastAddedDatabase>)

    @Query("select * from remote_keys_editor_choice where keyId=:id")
    suspend fun remoteKeysEditorChoiceId(id: Int): RemoteKeyEditorChoiceDatabase?

    @Query("select * from remote_keys_comments where keyId=:id")
    suspend fun remoteKeysCommentsId(id: Int): RemoteKeyCommentDatabase?

    @Query("delete from remote_keys_editor_choice")
    suspend fun deleteEditorChoice()

    @Query("delete from remote_keys_comments")
    suspend fun deleteComments()


    @Query("select * from remote_keys_last_added where keyId=:id")
    suspend fun remoteKeysLastAddedId(id: Int): RemoteKeyLastAddedDatabase?

    @Query("delete from remote_keys_last_added")
    suspend fun deleteLastAdded()

}