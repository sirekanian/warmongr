package com.sirekanian.acf.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TagDao {

    @Query("SELECT * FROM TagEntity")
    suspend fun getTags(): List<TagEntity>

}