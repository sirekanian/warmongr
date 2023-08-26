package org.sirekanyan.warmongr

import android.app.Application
import androidx.room.Room
import org.sirekanyan.warmongr.data.Repository
import org.sirekanyan.warmongr.data.RepositoryImpl
import org.sirekanyan.warmongr.data.local.Database

class App : Application() {
    private val db by lazy {
        Room.databaseBuilder(this, Database::class.java, "database")
            .fallbackToDestructiveMigration()
            .createFromAsset("warmongers.db")
            .build()
    }
    val repository: Repository by lazy { RepositoryImpl(db.getWarmongerDao(), db.getTagDao()) }
}