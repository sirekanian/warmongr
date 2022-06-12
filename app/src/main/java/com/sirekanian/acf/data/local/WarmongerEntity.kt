package com.sirekanian.acf.data.local

import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.FtsOptions

@Fts4(tokenizer = FtsOptions.TOKENIZER_UNICODE61)
@Entity
class WarmongerEntity(
    val cyrillicName: String,
    val name: String,
    val notes: String,
    val tags: String,
)