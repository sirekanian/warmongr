package org.sirekanyan.warmongr.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import org.sirekanyan.warmongr.D
import org.sirekanyan.warmongr.MainState
import org.sirekanyan.warmongr.WarmongerModel
import org.sirekanyan.warmongr.ext.plus
import org.sirekanyan.warmongr.ext.pointerInputOnDown

@Composable
fun MainContent(insets: PaddingValues, state: MainState, data: List<WarmongerModel>) {
    val bottomPadding = if (state.search.isOpened) 0.dp else D.fabSize + D.fabPadding
    val paddings = PaddingValues(top = D.toolbarSize, bottom = bottomPadding)
    val focusManager = LocalFocusManager.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .pointerInputOnDown(Unit) { focusManager.clearFocus() },
        state = state.list.lazyListState,
        contentPadding = insets + paddings + D.listPaddings,
        verticalArrangement = Arrangement.spacedBy(D.cardsSpacing)
    ) {
        items(data) { item ->
            WarmongerCard(state, item)
        }
    }
}
