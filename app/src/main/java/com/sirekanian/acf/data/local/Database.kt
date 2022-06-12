package com.sirekanian.acf.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        WarmongerEntity::class,
        MetaEntity::class,
        TagEntity::class,
        IndexEntity::class,
    ],
    version = 1,
)
abstract class Database : RoomDatabase() {

    abstract fun getWarmongerDao(): WarmongerDao
    abstract fun getMetaDao(): MetaDao
    abstract fun getTagDao(): TagDao

}