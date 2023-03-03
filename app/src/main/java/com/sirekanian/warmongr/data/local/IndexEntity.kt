package com.sirekanian.warmongr.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class IndexEntity(
    @PrimaryKey
    val key: String,
    val value: String,
)