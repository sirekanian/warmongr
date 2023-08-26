package org.sirekanyan.warmongr.data

import org.sirekanyan.warmongr.TagModel
import org.sirekanyan.warmongr.WarmongerModel
import org.sirekanyan.warmongr.data.local.WarmongerEntity
import org.sirekanyan.warmongr.data.remote.WarmongerDto

class Warmonger(
    val id: Int,
    val cyrillicName: String,
    val name: String,
    val notes: String,
    val tags: List<Int>,
) {

    companion object {

        fun fromDto(dto: WarmongerDto): Warmonger =
            Warmonger(
                id = 0, // TODO: 1202468796234411
                cyrillicName = dto.`0`,
                name = dto.`1`.ifEmpty { dto.`0` },
                notes = dto.`4`,
                tags = listOf(), // TODO: 1202468796234411
            )

        fun fromEntity(entity: WarmongerEntity): Warmonger =
            Warmonger(
                id = entity.rowid,
                cyrillicName = entity.cyrillicName,
                name = entity.name,
                notes = entity.notes,
                tags = entity.tags.split(" ").map(String::toInt),
            )

        fun toEntity(warmonger: Warmonger): WarmongerEntity =
            WarmongerEntity(
                rowid = warmonger.id,
                cyrillicName = warmonger.cyrillicName,
                name = warmonger.name,
                notes = warmonger.notes,
                tags = "" // TODO: 1202468796234411
            )

        fun toModel(warmonger: Warmonger, tags: List<TagModel>, cyrillic: Boolean): WarmongerModel =
            WarmongerModel(
                id = warmonger.id,
                title = if (cyrillic) warmonger.cyrillicName else warmonger.name,
                description = warmonger.notes,
                tags = tags,
            )

    }

}