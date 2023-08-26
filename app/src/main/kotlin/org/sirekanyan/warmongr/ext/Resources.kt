package com.sirekanian.warmongr.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.booleanResource
import com.sirekanian.warmongr.R

@Composable
fun isCyrillicResources(): Boolean =
    booleanResource(R.bool.app_cyrillic)
