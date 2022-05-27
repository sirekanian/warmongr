package com.sirekanian.acf.ext

import android.content.Intent

fun createShareIntent(text: String): Intent =
    Intent(Intent.ACTION_SEND)
        .putExtra(Intent.EXTRA_TEXT, text)
        .setType("text/plain")
