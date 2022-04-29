package com.sirekanian.acf.data

import com.sirekanian.acf.data.local.WarmongerEntity
import com.sirekanian.acf.data.remote.WarmongerDto

class Warmonger(
    val cyrillicName: String,
    val name: String,
    val notes: String,
) {

    companion object {

        fun fromDto(dto: WarmongerDto): Warmonger =
            Warmonger(
                cyrillicName = dto.`0`,
                name = dto.`1`.ifEmpty { dto.`0` },
                notes = dto.`4`,
            )

        fun fromEntity(entity: WarmongerEntity): Warmonger =
            Warmonger(
                cyrillicName = entity.cyrillicName,
                name = entity.name,
                notes = entity.notes,
            )

        fun toEntity(model: Warmonger): WarmongerEntity =
            WarmongerEntity(
                cyrillicName = model.cyrillicName,
                name = model.name,
                notes = model.notes,
            )

    }

}