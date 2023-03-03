package com.sirekanian.acf.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MetaEntity(
    @PrimaryKey
    val key: String,
    val value: String,
)