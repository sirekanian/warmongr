package com.sirekanian.acf

import com.sirekanian.acf.data.Repository
import com.sirekanian.acf.data.Warmonger
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

private val UPDATE_PERIOD = if (BuildConfig.DEBUG) 20.seconds else 5.minutes

fun createPresenter(app: App, state: MainState): MainPresenter =
    MainPresenterImpl(app.repository, state)

interface MainPresenter {
    fun observeData(): Flow<List<Warmonger>>
    suspend fun updateData()
}

class MainPresenterImpl(
    private val repository: Repository,
    private val state: MainState,
) : MainPresenter {

    override fun observeData(): Flow<List<Warmonger>> =
        if (state.search.isOpened) {
            repository.observeByQuery(state.search.query.text)
        } else {
            repository.observeAll()
        }

    override suspend fun updateData() =
        withContext(IO) {
            while (true) {
                repository.updateFromRemote(state.progress)
                delay(UPDATE_PERIOD)
            }
        }

}