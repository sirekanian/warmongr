package org.sirekanyan.warmongr

import org.sirekanyan.warmongr.data.Repository
import org.sirekanyan.warmongr.data.Tag
import org.sirekanyan.warmongr.data.Warmonger
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

fun createPresenter(app: App, state: MainState): MainPresenter =
    MainPresenterImpl(app.repository, state)

interface MainPresenter {
    suspend fun getWarmongers(): List<WarmongerModel>
    suspend fun getTags(): List<TagModel>
    suspend fun updateData()
}

class MainPresenterImpl(
    private val repository: Repository,
    private val state: MainState,
) : MainPresenter {

    override suspend fun getWarmongers(): List<WarmongerModel> {
        val allTags = getTags().associateBy(TagModel::id)
        return repository.getWarmongers(state.search.fullQuery).map { warmonger ->
            val tags = warmonger.tags.mapNotNull { allTags[it] }
            Warmonger.toModel(warmonger, tags, state.isCyrillic)
        }
    }

    override suspend fun getTags(): List<TagModel> =
        repository.getTags().map { tag ->
            Tag.toModel(tag, state.isCyrillic)
        }

    @Suppress("unused") // TODO: 1202468796234411
    override suspend fun updateData() =
        withContext(IO) {
            repository.updateFromRemote(state.progress)
        }

}