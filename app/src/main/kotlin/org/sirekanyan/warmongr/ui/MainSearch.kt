package org.sirekanyan.warmongr.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import org.sirekanyan.warmongr.D
import org.sirekanyan.warmongr.R
import org.sirekanyan.warmongr.SearchState
import org.sirekanyan.warmongr.ext.DefaultAnimatedVisibility
import org.sirekanyan.warmongr.ui.icons.IconBack
import org.sirekanyan.warmongr.ui.icons.IconClose

@Composable
fun MainSearch(searchState: SearchState) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    Row(verticalAlignment = Alignment.CenterVertically) {
        ToolbarButton(
            icon = IconBack,
            onClick = {
                focusManager.clearFocus()
                searchState.isOpened = false
            }
        )
        SearchTextField(
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester),
            searchState = searchState,
            clearFocus = { focusManager.clearFocus() },
            requestFocus = { focusRequester.requestFocus() }
        )
        ToolbarButton(
            icon = IconClose,
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
            Icon(icon, null, Modifier.padding(D.toolbarButtonPadding))
        }
    }
}
