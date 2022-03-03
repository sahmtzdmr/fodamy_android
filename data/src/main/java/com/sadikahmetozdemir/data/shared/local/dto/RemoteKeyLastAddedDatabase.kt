package com.sadikahmetozdemir.data.shared.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "remote_keys_last_added")
data class RemoteKeyLastAddedDatabase(
    @PrimaryKey
    val keyId: Int,
    val prevKey: Int?,
    val nextKey: Int?

)
