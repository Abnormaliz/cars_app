package com.example.thecars.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class CarAndNote(
    @Embedded val car: CarEntity,
    @Relation(
        parentColumn = "carName",
        entityColumn = "carName"
    )
    val note: NotesEntity?
)
