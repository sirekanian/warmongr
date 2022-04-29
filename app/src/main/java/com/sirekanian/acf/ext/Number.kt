package com.sirekanian.acf.ext

fun calculateProgressOrNull(current: Long, total: Long): Float? =
    if (total == 0L) null else (current.toFloat() / total).coerceIn(0f, 1f)
