package com.sirekanian.acf.data

import com.sirekanian.acf.TagModel
import com.sirekanian.acf.data.local.TagEntity

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