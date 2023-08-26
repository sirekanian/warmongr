package com.sirekanian.warmongr.data

import com.sirekanian.warmongr.TagModel
import com.sirekanian.warmongr.data.local.TagEntity

class Tag(
    val id: Int,
    val name: String,
    val ruName: String,
) {

    companion object {

        fun fromEntity(entity: TagEntity): Tag =
            Tag(
                id = entity.id,
                name = entity.name,
                ruName = entity.ruName,
            )

        fun toModel(tag: Tag, isCyrillic: Boolean): TagModel =
            TagModel(
                id = tag.id,
                name = if (isCyrillic) tag.ruName else tag.name
            )

    }

}