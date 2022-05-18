package com.sirekanian.acf

import android.app.Application
import androidx.room.Room
import com.sirekanian.acf.data.Repository
import com.sirekanian.acf.data.RepositoryImpl
import com.sirekanian.acf.data.local.Database

class App : Application() {
    private val db by lazy {
        Room.databaseBuilder(this, Database::class.java, "database")
            .createFromAsset("warmongers.db")
            .build()
    }
    val repository: Repository by lazy { RepositoryImpl(db.warmongerDao()) }
}