package com.sirekanian.warmongr

import com.sirekanian.warmongr.data.Repository
import com.sirekanian.warmongr.data.Tag
import com.sirekanian.warmongr.data.Warmonger
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

    override suspend fun getWarmongers(): List<WarmongerModel> =
        repository.getWarmongers(state.search.fullQuery).map { warmonger ->
            Warmonger.toModel(warmonger, state.isCyrillic)
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