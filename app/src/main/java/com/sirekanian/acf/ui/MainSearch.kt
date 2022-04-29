package com.sirekanian.acf.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.sirekanian.acf.R
import com.sirekanian.acf.SearchState
import com.sirekanian.acf.ext.DefaultAnimatedVisibility

@Composable
fun MainSearch(searchState: SearchState) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    Row(verticalAlignment = Alignment.CenterVertically) {
        ToolbarButton(
            icon = Icons.Default.ArrowBack,
            onClick = {
                focusManager.clearFocus()
                searchState.isOpened = false
            }
        )
        SearchTextField(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .focusRequester(focusRequester),
            searchState = searchState,
            clearFocus = { focusManager.clearFocus() },
            requestFocus = { focusRequester.requestFocus() }
        )
        ToolbarButton(
            icon = Icons.Default.Close,
            visible = searchState.query.text.isNotEmpty(),
            onClick = {
                searchState.query = TextFieldValue()
                focusRequester.requestFocus()
            }
        )
    }
}

@Composable
private fun SearchTextField(
    modifier: Modifier,
    searchState: SearchState,
    clearFocus: () -> Unit,
    requestFocus: () -> Unit,
) {
    BasicTextField(
        value = searchState.query,
        onValueChange = { searchState.query = it },
        modifier = modifier,
        textStyle = MaterialTheme.typography.h6.copy(color = LocalContentColor.current),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { clearFocus() }),
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        decorationBox = { innerTextField ->
            Box {
                if (searchState.query.text.isEmpty()) {
                    Text(
                        text = stringResource(R.string.app_search_hint),
                        modifier = Modifier.alpha(ContentAlpha.disabled),
                        style = MaterialTheme.typography.h6.copy(color = LocalContentColor.current)
                    )
                }
                innerTextField()
            }
        }
    )
    LaunchedEffect(Unit) {
        requestFocus()
    }
}

@Composable
private fun ToolbarButton(icon: ImageVector, visible: Boolean = true, onClick: () -> Unit) {
    DefaultAnimatedVisibility(visible = visible) {
        IconButton(onClick = onClick) {
            Icon(icon, null)
        }
    }
}
