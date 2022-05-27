package com.sirekanian.acf

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainState(coroutineScope: CoroutineScope, val isCyrillic: Boolean) {
    val list = ListState()
    val search = SearchState()
    val progress = ProgressState()
    val toolbarElevation by derivedStateOf {
        if (search.isOpened || !list.isTopVisible) {
            D.toolbarElevation
        } else {
            0.dp
        }
    }
    val dialog = DialogState(coroutineScope)
}

class ListState {
    val lazyListState = LazyListState()
    val isTopVisible by derivedStateOf {
        lazyListState.firstVisibleItemIndex == 0 &&
                lazyListState.firstVisibleItemScrollOffset == 0
    }
}

class SearchState {
    var query by mutableStateOf(TextFieldValue())
    var isOpened by mutableStateOf(false)
}

class ProgressState {

    private var current by mutableStateOf<Long?>(null)
    private var total by mutableStateOf<Long?>(null)

    fun isVisible(): Boolean =
        current != null && total != null

    fun show() {
        current = 0
        total = 1
    }

    fun hide() {
        current = null
        total = null
    }

    fun setIndeterminate() {
        current = 0
        total = 0
    }

    fun setDeterminate(current: Long, total: Long) {
        this.current = current
        this.total = total
    }

    fun calculateProgressOrNull(): Float? {
        val current = current ?: return null
        val total = total ?: return null
        if (total == 0L) return null
        return (current.toFloat() / total).coerceIn(0f, 1f)
    }

}

@OptIn(ExperimentalMaterialApi::class)
class DialogState(private val coroutineScope: CoroutineScope) {

    val bottomSheetState = ModalBottomSheetState(Hidden)
    var title by mutableStateOf("")
    var content by mutableStateOf("")

    fun show(model: WarmongerModel) {
        title = model.title
        content = model.content
        coroutineScope.launch {
            bottomSheetState.show()
        }
    }

    fun hide() {
        coroutineScope.launch {
            bottomSheetState.hide()
        }
    }

}
