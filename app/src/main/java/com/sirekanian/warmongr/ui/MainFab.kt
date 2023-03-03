package com.sirekanian.warmongr.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sirekanian.warmongr.D
import com.sirekanian.warmongr.R
import com.sirekanian.warmongr.ui.icons.IconSearch

@Composable
fun MainFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.size(D.fabSize),
        shape = D.fabShape,
    ) {
        Icon(
            imageVector = IconSearch,
            contentDescription = stringResource(R.string.app_search_hint),
            modifier = Modifier.size(D.fabIconSize),
        )
    }
}
