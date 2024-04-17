package com.example.thecars.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "list_notes")
data class NotesEntity(
    val text: String,
    @PrimaryKey
    val carName: String,
    //@PrimaryKey(autoGenerate = true)
    //val id: Int = 0
)
