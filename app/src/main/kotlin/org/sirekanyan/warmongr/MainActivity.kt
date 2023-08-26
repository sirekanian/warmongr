package org.sirekanyan.warmongr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
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
import org.sirekanyan.warmongr.ext.DefaultAnimatedVisibility
import org.sirekanyan.warmongr.ext.app
import org.sirekanyan.warmongr.ui.*
import org.sirekanyan.warmongr.ui.theme.WarmongrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val state = rememberMainState()
            val presenter = remember { createPresenter(app(), state) }
            val data: List<WarmongerModel> by produceState(
                initialValue = listOf(),
                key1 = state.search.fullQuery,
            ) {
                value = presenter.getWarmongers()
            }
            val tags: List<TagModel> by produceState(
                initialValue = listOf()
            ) {
                value = presenter.getTags()
            }
            val hasData by remember { derivedStateOf { data.isNotEmpty() } }
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
                        state = state,
                        toolbar = { insets ->
                            Column(
                                modifier = Modifier
                                    .padding(insets)
                                    .fillMaxWidth()
                                    .height(D.toolbarSize)
                            ) {
                                MainToolbar(state.search)
                                MainTags(D.tagsPaddings, state.search, tags)
                            }
                            MainProgress(insets, state.progress)
                        },
                        toolbarElevation = state.toolbarElevation,
                        content = { insets -> MainContent(insets, state, data) },
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
    state: MainState,
    toolbar: @Composable (PaddingValues) -> Unit,
    toolbarElevation: Dp,
    content: @Composable (PaddingValues) -> Unit,
    contentVisible: Boolean,
    fab: @Composable () -> Unit,
    fabVisible: Boolean,
) {
    MainBottomSheet(dialogState = state.dialog) {
        DefaultAnimatedVisibility(contentVisible) {
            content(WindowInsets.systemBars.asPaddingValues())
        }
        Surface(Modifier.fillMaxWidth(), elevation = toolbarElevation) {
            toolbar(WindowInsets.statusBars.asPaddingValues())
        }
        DefaultAnimatedVisibility(fabVisible) {
            BottomBox(Modifier.padding(D.fabPadding)) {
                fab()
            }
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
