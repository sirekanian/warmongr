package com.sirekanian.warmongr.ext

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.pointerInputOnDown(key: Any?, onDownEvent: () -> Unit): Modifier =
    pointerInput(key) {
        forEachGesture {
            awaitPointerEventScope {
                awaitFirstDown(requireUnconsumed = false)
                onDownEvent()
            }
        }
    }
