package org.sirekanyan.warmongr.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TagDao {

    @Query("SELECT * FROM TagEntity")
    suspend fun getTags(): List<TagEntity>

}