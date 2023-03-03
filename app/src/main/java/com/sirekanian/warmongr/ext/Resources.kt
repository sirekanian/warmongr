package com.sirekanian.acf.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.booleanResource
import com.sirekanian.acf.R

@Composable
fun isCyrillicResources(): Boolean =
    booleanResource(R.bool.app_cyrillic)
