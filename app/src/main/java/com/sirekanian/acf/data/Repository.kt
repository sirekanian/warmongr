package com.sirekanian.acf.data

import android.database.sqlite.SQLiteException
import com.sirekanian.acf.ProgressState
import com.sirekanian.acf.data.local.WarmongerDao
import com.sirekanian.acf.data.local.WarmongerEntity
import com.sirekanian.acf.data.remote.getWarmongers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

interface Repository {
    fun observeAll(): Flow<List<Warmonger>>
    fun observeByQuery(query: String): Flow<List<Warmonger>>
    suspend fun updateFromRemote(progressState: ProgressState)
}

class RepositoryImpl(
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