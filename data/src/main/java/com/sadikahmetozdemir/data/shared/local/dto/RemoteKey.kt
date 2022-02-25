package com.sadikahmetozdemir.data.shared.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
class RemoteKey(
    @PrimaryKey
    val keyId: String,
    val prevKey: Int,
    val nextKey: Int

)
