package com.sirekanian.acf

class WarmongerModel(val title: String, val description: String) {
    val content get() = "$title\n\n$description"
}