package com.sadikahmetozdemir.data.shared.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys_editor_choice")
data class RemoteKeyEditorChoiceDatabase(
    @PrimaryKey
    val keyId: Int,
    val prevKey: Int?,
    val nextKey: Int?

)
