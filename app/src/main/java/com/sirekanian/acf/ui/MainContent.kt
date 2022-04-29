package com.sirekanian.acf.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.sirekanian.acf.D
import com.sirekanian.acf.ListState
import com.sirekanian.acf.data.Warmonger
import com.sirekanian.acf.ext.plus
import com.sirekanian.acf.ext.pointerInputOnDown

@Composable
fun MainContent(insets: PaddingValues, listState: ListState, data: List<Warmonger>) {
    val paddings = PaddingValues(top = D.toolbarSize, bottom = D.fabSize + D.fabPadding)
    val focusManager = LocalFocusManager.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .pointerInputOnDown(Unit) { focusManager.clearFocus() },
        state = listState.lazyListState,
        contentPadding = insets + paddings + D.listPaddings,
        verticalArrangement = Arrangement.spacedBy(D.cardSpacing)
    ) {
        items(data) { item ->
            WarmongerCard(item)
        }
    }
}
