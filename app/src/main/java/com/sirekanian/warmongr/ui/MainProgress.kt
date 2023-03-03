package com.sirekanian.acf.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.sirekanian.acf.D
import com.sirekanian.acf.ProgressState

@Composable
fun MainProgress(insets: PaddingValues, progressState: ProgressState) {
    AnimatedVisibility(visible = progressState.isVisible(), enter = fadeIn(), exit = fadeOut()) {
        val progress = progressState.calculateProgressOrNull()
        val modifier = Modifier
            .padding(insets)
            .padding(top = D.toolbarSize - D.progressSize)
            .fillMaxWidth()
            .height(D.progressSize)
            .alpha(ContentAlpha.disabled)
        val color = MaterialTheme.colors.secondary
        if (progress == null) {
            LinearProgressIndicator(modifier, color)
        } else {
            LinearProgressIndicator(progress, modifier, color)
        }
    }
}