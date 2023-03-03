package com.sirekanian.acf.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.sirekanian.acf.D
import com.sirekanian.acf.R
import com.sirekanian.acf.SearchState

@Composable
fun MainToolbar(searchState: SearchState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(D.toolbarHeaderSize),
        contentAlignment = Alignment.Center,
    ) {
        if (searchState.isOpened) {
            MainSearch(searchState)
        } else {
            ToolbarText()
        }
    }
}

@Composable
private fun ToolbarText() {
    val text = buildAnnotatedString {
        append(stringResource(R.string.app_name_part_1))
        withStyle(SpanStyle(MaterialTheme.colors.primary)) {
            append(stringResource(R.string.app_name_part_2))
        }
        append(stringResource(R.string.app_name_part_3))
    }
    Text(
        text = text,
        modifier = Modifier.padding(horizontal = 16.dp),
        style = MaterialTheme.typography.h6,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}
