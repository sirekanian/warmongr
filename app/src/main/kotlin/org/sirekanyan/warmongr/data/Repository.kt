package org.sirekanyan.warmongr.data

import android.database.sqlite.SQLiteException
import org.sirekanyan.warmongr.ProgressState
import org.sirekanyan.warmongr.data.local.TagDao
import org.sirekanyan.warmongr.data.local.WarmongerDao
import org.sirekanyan.warmongr.data.remote.getWarmongers

interface Repository {
    suspend fun getWarmongers(query: String?): List<Warmonger>
    suspend fun getTags(): List<Tag>
    suspend fun updateFromRemote(progressState: ProgressState)
}

class RepositoryImpl(
    private val dao: WarmongerDao,
    private val tagDao: TagDao,
) : Repository {

    override suspend fun getWarmongers(query: String?): List<Warmonger> =
        when {
            query == null -> dao.getAll()
            query.isEmpty() -> listOf()
            else -> {
                try {
                    dao.getByQuery(query)
                } catch (ex: SQLiteException) {
                    // TODO: 1202308718694574
                    listOf()
                }
            }
        }.map(Warmonger::fromEntity)

    override suspend fun getTags(): List<Tag> =
        tagDao.getTags().map(Tag::fromEntity)

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