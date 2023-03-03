package com.sirekanian.warmongr

class WarmongerModel(val id: Int, val title: String, val description: String) {
    val content get() = "$title\n\n$description"
}