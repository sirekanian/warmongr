package com.sirekanian.acf.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.sirekanian.acf.R

private val customFontFamily = FontFamily(
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
)

val Typography = Typography(
    customFontFamily,
    h1 = Type3.displayLarge,
    h2 = Type3.displayMedium,
    h3 = Type3.headlineLarge,
    h4 = Type3.headlineMedium,
    h5 = Type3.headlineSmall,
    h6 = Type3.titleLarge,
    subtitle1 = Type3.titleMedium,
    subtitle2 = Type3.titleSmall,
    body1 = Type3.bodyLarge,
    body2 = Type3.bodyMedium,
    button = Type3.labelLarge,
    caption = Type3.labelMedium,
    overline = Type3.labelSmall,
)
