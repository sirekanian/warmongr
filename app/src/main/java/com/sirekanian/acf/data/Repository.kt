package com.sirekanian.acf.data

import android.content.res.Resources
import android.database.sqlite.SQLiteException
import com.sirekanian.acf.ProgressState
import com.sirekanian.acf.R
import com.sirekanian.acf.data.local.WarmongerDao
import com.sirekanian.acf.data.local.WarmongerEntity
import com.sirekanian.acf.data.remote.WarmongerDto
import com.sirekanian.acf.data.remote.getWarmongers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface Repository {
    fun observeAll(): Flow<List<Warmonger>>
    fun observeByQuery(query: String): Flow<List<Warmonger>>
    suspend fun hasData(): Boolean
    suspend fun initLocal()
    suspend fun updateFromRemote(progressState: ProgressState)
}

class RepositoryImpl(
    private val resources: Resources,
    private val dao: WarmongerDao,
) : Repository {

    override fun observeAll(): Flow<List<Warmonger>> =
        dao.observeAll().fromEntities()

    override fun observeByQuery(query: String): Flow<List<Warmonger>> =
        dao.observeByQuery("$query*").fromEntities()

    private fun Flow<List<WarmongerEntity>>.fromEntities(): Flow<List<Warmonger>> =
        map { it.map(Warmonger::fromEntity) }.catch {
            if (it is SQLiteException) {
                emit(listOf())
            } else {
                throw it
            }
        }

    override suspend fun hasData(): Boolean =
        dao.hasData()

    override suspend fun initLocal() {
        val content = resources.openRawResource(R.raw.default1).bufferedReader().readText()
        val local = Json.decodeFromString<List<WarmongerDto>>(content)
        saveToLocal(local.map(Warmonger::fromDto))
    }

    override suspend fun updateFromRemote(progressState: ProgressState) {
        try {
            progressState.show()
            val remote = getWarmongers(progressState::setDeterminate).map(Warmonger::fromDto)
            progressState.setIndeterminate()
            saveToLocal(remote)
        } finally {
            progressState.hide()
        }
    }

    private suspend fun saveToLocal(users: List<Warmonger>) {
        dao.deleteAndInsertAll(users.map(Warmonger::toEntity))
    }

}