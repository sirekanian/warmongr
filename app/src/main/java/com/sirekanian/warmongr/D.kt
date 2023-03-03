package com.sirekanian.acf

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object D {
    val listPaddings = PaddingValues(16.dp, 8.dp, 16.dp, 16.dp)
    val tagsPaddings = PaddingValues(16.dp, 0.dp, 16.dp, 8.dp)
    val tagsSpacing = 8.dp
    val tagHeight = 32.dp
    val tagShape = RoundedCornerShape(8.dp)
    val tagBorder = 1.dp
    val tagPaddings = 8.dp
    val tagIconSize = 18.dp
    val toolbarHeaderSize = 64.dp
    val toolbarSize: Dp =
        toolbarHeaderSize + tagHeight +
                tagsPaddings.calculateTopPadding() +
                tagsPaddings.calculateBottomPadding()
    val toolbarElevation = 8.dp
    val toolbarButtonPadding = 16.dp
    val fabPadding = 16.dp
    val fabSize = 96.dp
    val fabShape = RoundedCornerShape(28.dp)
    val fabIconSize = 36.dp
    val cardsSpacing = 8.dp
    val cardDefaultCornerSize = 28.dp
    val cardSelectedCornerSize = 12.dp
    val cardDefaultElevation = 2.dp
    val cardSelectedElevation = 4.dp
    val progressSize = 2.dp
    val dialogTitlePadding = 16.dp
    val menuIconPadding = 16.dp
    val menuTextPadding = 8.dp
}