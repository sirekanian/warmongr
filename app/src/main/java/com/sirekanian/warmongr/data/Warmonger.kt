package com.sirekanian.warmongr.data

import com.sirekanian.warmongr.WarmongerModel
import com.sirekanian.warmongr.data.local.WarmongerEntity
import com.sirekanian.warmongr.data.remote.WarmongerDto

class Warmonger(
    val id: Int,
    val cyrillicName: String,
    val name: String,
    val notes: String,
) {

    companion object {

        fun fromDto(dto: WarmongerDto): Warmonger =
            Warmonger(
                id = 0, // TODO: 1202468796234411
                cyrillicName = dto.`0`,
                name = dto.`1`.ifEmpty { dto.`0` },
                notes = dto.`4`,
            )

        fun fromEntity(entity: WarmongerEntity): Warmonger =
            Warmonger(
                id = entity.rowid,
                cyrillicName = entity.cyrillicName,
                name = entity.name,
                notes = entity.notes,
            )

        fun toEntity(warmonger: Warmonger): WarmongerEntity =
            WarmongerEntity(
                rowid = warmonger.id,
                cyrillicName = warmonger.cyrillicName,
                name = warmonger.name,
                notes = warmonger.notes,
                tags = "" // TODO: 1202468796234411
            )

        fun toModel(warmonger: Warmonger, isCyrillic: Boolean): WarmongerModel =
            WarmongerModel(
                id = warmonger.id,
                title = if (isCyrillic) warmonger.cyrillicName else warmonger.name,
                description = warmonger.notes,
            )

    }

}