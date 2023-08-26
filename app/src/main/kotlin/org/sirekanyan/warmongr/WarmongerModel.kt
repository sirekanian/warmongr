package org.sirekanyan.warmongr

class WarmongerModel(val id: Int, val title: String, val description: String, val tags: List<TagModel>) {
    val content get() = "$title\n\n$description"
}