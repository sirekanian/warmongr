package com.sirekanian.acf

import com.sirekanian.acf.data.Repository
import com.sirekanian.acf.data.Warmonger
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

fun createPresenter(app: App, state: MainState): MainPresenter =
    MainPresenterImpl(app.repository, state)

interface MainPresenter {
    fun observeData(): Flow<List<WarmongerModel>>
    suspend fun updateData()
}

class MainPresenterImpl(
    private val repository: Repository,
    private val state: MainState,
) : MainPresenter {

    override fun observeData(): Flow<List<WarmongerModel>> =
        if (state.search.isOpened) {
            repository.observeByQuery(state.search.query.text)
        } else {
            repository.observeAll()
        }.map { warmongers ->
            warmongers.map { warmonger ->
                Warmonger.toModel(warmonger, state.isCyrillic)
            }
        }

    override suspend fun updateData() =
        withContext(IO) {
            repository.updateFromRemote(state.progress)
        }

}