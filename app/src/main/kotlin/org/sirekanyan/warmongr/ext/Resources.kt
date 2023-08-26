package org.sirekanyan.warmongr.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.booleanResource
import org.sirekanyan.warmongr.R

@Composable
fun isCyrillicResources(): Boolean =
    booleanResource(R.bool.app_cyrillic)
