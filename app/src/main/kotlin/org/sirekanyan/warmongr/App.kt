package com.sirekanian.warmongr

import android.app.Application
import androidx.room.Room
import com.sirekanian.warmongr.data.Repository
import com.sirekanian.warmongr.data.RepositoryImpl
import com.sirekanian.warmongr.data.local.Database

class App : Application() {
    private val db by lazy {
        Room.databaseBuilder(this, Database::class.java, "database")
            .fallbackToDestructiveMigration()
            .createFromAsset("warmongers.db")
            .build()
    }
    val repository: Repository by lazy { RepositoryImpl(db.getWarmongerDao(), db.getTagDao()) }
}