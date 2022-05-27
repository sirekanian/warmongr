package com.sirekanian.acf.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sirekanian.acf.D
import com.sirekanian.acf.R
import com.sirekanian.acf.ui.icons.IconSearch

@Composable
fun MainFab(onClick: () -> Unit) {
    FloatingActionButton(modifier = Modifier.size(D.fabSize), onClick = onClick) {
        Icon(IconSearch, stringResource(R.string.app_search_hint))
    }
}
