package org.sirekanyan.warmongr.ext

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

fun Color.isLightColor(): Boolean =
    ColorUtils.calculateLuminance(toArgb()) < 0.18
