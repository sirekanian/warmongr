package com.sirekanian.warmongr.ext

import android.content.Intent

fun createShareIntent(text: String): Intent =
    Intent(Intent.ACTION_SEND)
        .putExtra(Intent.EXTRA_TEXT, text)
        .setType("text/plain")
