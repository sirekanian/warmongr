package com.sirekanian.acf.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TagEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val ruName: String,
)