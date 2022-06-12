package com.sirekanian.acf.data

import android.database.sqlite.SQLiteException
import com.sirekanian.acf.ProgressState
import com.sirekanian.acf.data.local.TagDao
import com.sirekanian.acf.data.local.WarmongerDao
import com.sirekanian.acf.data.local.WarmongerEntity
import com.sirekanian.acf.data.remote.getWarmongers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

interface Repository {
    fun observeByQuery(query: String?): Flow<List<Warmonger>>
    suspend fun getTags(): List<Tag>
    suspend fun updateFromRemote(progressState: ProgressState)
}

class RepositoryImpl(
    private val dao: WarmongerDao,
    private val tagDao: TagDao,
) : Repository {

    override fun observeByQuery(query: String?): Flow<List<Warmonger>> =
        when {
            query == null -> dao.observeAll()
            query.isEmpty() -> flowOf(listOf())
            else -> dao.observeByQuery(query)
        }.fromEntities()

    override suspend fun getTags(): List<Tag> =
        tagDao.getTags().map(Tag::fromEntity)

    private fun Flow<List<WarmongerEntity>>.fromEntities(): Flow<List<Warmonger>> =
        map { it.map(Warmonger::fromEntity) }.catch {
            if (it is SQLiteException) {
                // TODO: 1202308718694574
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