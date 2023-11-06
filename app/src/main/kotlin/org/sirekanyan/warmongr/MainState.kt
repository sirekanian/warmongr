package org.sirekanyan.warmongr

import android.os.Parcelable
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import org.sirekanyan.warmongr.ext.isCyrillicResources

@Composable
fun rememberMainState(): MainState {
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current
    val isCyrillic = isCyrillicResources()
    val searchState = rememberSaveable(saver = SearchState.Saver) { SearchState() }
    return remember { MainState(coroutineScope, density, isCyrillic, searchState) }
}

class MainState(
    coroutineScope: CoroutineScope,
    density: Density,
    val isCyrillic: Boolean,
    val search: SearchState,
) {
    val list = ListState()
    val progress = ProgressState()
    val toolbarElevation by derivedStateOf {
        if (search.isOpened || !list.isTopVisible) {
            D.toolbarElevation
        } else {
            0.dp
        }
    }
    val dialog = DialogState(coroutineScope, density)
}

class ListState {
    val lazyListState = LazyListState()
    val isTopVisible by derivedStateOf {
        lazyListState.firstVisibleItemIndex == 0 &&
                lazyListState.firstVisibleItemScrollOffset == 0
    }
}

class SearchState {

    var tag by mutableStateOf<Int?>(null)
    var query by mutableStateOf(TextFieldValue())
    var isOpened by mutableStateOf(false)
    val fullQuery: String? by derivedStateOf {
        if (isOpened) {
            val textQuery = if (query.text.isEmpty()) null else "${query.text}*"
            val tagsQuery = if (tag == null) null else "tags:$tag"
            listOfNotNull(textQuery, tagsQuery).joinToString(" ")
        } else {
            tag?.let { "tags:$it" }
        }
    }

    companion object {

        @Parcelize
        class Saveable(
            val isOpened: Boolean,
            val tag: Int?,
            val query: String,
            val start: Int,
            val end: Int,
        ) : Parcelable

        val Saver = Saver<SearchState, Saveable>(
            save = {
                Saveable(
                    it.isOpened,
                    it.tag,
                    it.query.text,
                    it.query.selection.start,
                    it.query.selection.end,
                )
            },
            restore = {
                SearchState().apply {
                    isOpened = it.isOpened
                    tag = it.tag
                    query = TextFieldValue(it.query, TextRange(it.start, it.end))
                }
            },
        )

    }

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
class DialogState(private val coroutineScope: CoroutineScope, density: Density) {

    val bottomSheetState = ModalBottomSheetState(Hidden, density)
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
