package com.sirekanian.acf.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sirekanian.acf.D
import com.sirekanian.acf.R

@Composable
fun MainFab(onClick: () -> Unit) {
    FloatingActionButton(modifier = Modifier.size(D.fabSize), onClick = onClick) {
        Icon(Icons.Default.Search, stringResource(R.string.app_search_hint))
    }
}
