package org.sirekanyan.warmongr.ext

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun DefaultAnimatedVisibility(visible: Boolean, content: @Composable () -> Unit) =
    AnimatedVisibility(visible = visible, enter = fadeIn(), exit = fadeOut()) {
        content()
    }

@Composable
fun AdvancedAnimatedVisibility(visible: Boolean, content: @Composable () -> Unit) =
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandIn(expandFrom = Alignment.Center),
        exit = fadeOut() + shrinkOut(shrinkTowards = Alignment.Center),
    ) {
        content()
    }
