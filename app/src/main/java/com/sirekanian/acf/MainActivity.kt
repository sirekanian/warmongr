package com.sirekanian.acf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.core.view.WindowCompat
import com.sirekanian.acf.ext.app
import com.sirekanian.acf.ui.MainContent
import com.sirekanian.acf.ui.MainFab
import com.sirekanian.acf.ui.MainProgress
import com.sirekanian.acf.ui.MainToolbar
import com.sirekanian.acf.ui.theme.WarmongrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val state = remember { MainState() }
            val presenter = remember { createPresenter(app(), state) }
            val data by presenter.observeData().collectAsState(listOf())
            val hasData by derivedStateOf { data.isNotEmpty() }
            BackHandler(enabled = state.search.isOpened) {
                state.search.isOpened = false
            }
            WarmongrTheme {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    MainLayout(
                        toolbar = { insets ->
                            MainToolbar(insets, state.search)
                            MainProgress(insets, state.progress)
                        },
                        toolbarElevation = state.toolbarElevation,
                        content = { insets -> MainContent(insets, state.list, data) },
                        contentVisible = hasData,
                        fab = { MainFab(onClick = { state.search.isOpened = true }) },
                        fabVisible = !state.search.isOpened,
                    )
                }
            }
        }
    }
}

@Composable
fun MainLayout(
    toolbar: @Composable (PaddingValues) -> Unit,
    toolbarElevation: Dp,
    content: @Composable (PaddingValues) -> Unit,
    contentVisible: Boolean,
    fab: @Composable () -> Unit,
    fabVisible: Boolean,
) {
    AnimatedVisibility(contentVisible, enter = fadeIn(), exit = fadeOut()) {
        content(WindowInsets.systemBars.asPaddingValues())
    }
    Surface(Modifier.fillMaxWidth(), elevation = toolbarElevation) {
        toolbar(WindowInsets.statusBars.asPaddingValues())
    }
    AnimatedVisibility(fabVisible, enter = fadeIn(), exit = fadeOut()) {
        BottomBox(Modifier.padding(D.fabPadding)) {
            fab()
        }
    }
    if (MaterialTheme.colors.isLight) {
        BottomBox {
            Divider()
        }
    }
}

@Composable
fun BottomBox(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier
            .navigationBarsPadding()
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        content()
    }
}
