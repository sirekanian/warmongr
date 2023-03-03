package com.sirekanian.warmongr.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MetaDao {

    @Query("SELECT value FROM MetaEntity WHERE `key` = :key LIMIT 1")
    suspend fun find(key: String): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun put(meta: MetaEntity)

}