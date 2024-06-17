package com.example.thecars.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(tableName = "list_notes", foreignKeys = arrayOf(ForeignKey(
    entity = CarEntity::class,
    parentColumns = arrayOf("carName"),
    childColumns = arrayOf("carName"),
    onDelete = ForeignKey.CASCADE
)))
data class NotesEntity(
    val text: String?,
    @PrimaryKey(autoGenerate = false)
    val carName: String,
)
