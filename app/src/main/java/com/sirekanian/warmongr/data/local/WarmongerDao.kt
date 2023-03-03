package com.sirekanian.warmongr.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

private const val LIMIT = 200

@Dao
interface WarmongerDao {

    @Query("SELECT rowid, * FROM WarmongerEntity ORDER BY cyrillicName LIMIT $LIMIT")
    suspend fun getAll(): List<WarmongerEntity>

    @Query("SELECT rowid, * FROM WarmongerEntity WHERE WarmongerEntity MATCH :query LIMIT $LIMIT")
    suspend fun getByQuery(query: String): List<WarmongerEntity>

    @Query("DELETE FROM WarmongerEntity")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(users: List<WarmongerEntity>)

    @Transaction
    suspend fun deleteAndInsertAll(users: List<WarmongerEntity>) {
        deleteAll()
        insertAll(users)
    }

}