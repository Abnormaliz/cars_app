package com.example.thecars.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.thecars.data.classes.Car

@Entity(tableName = "list_notes", foreignKeys = arrayOf(ForeignKey(
    entity = Car::class,
    parentColumns = arrayOf("car"),
    childColumns = arrayOf("carName"),
    onDelete = ForeignKey.CASCADE
)))
data class Note(
    val text: String?,
    @PrimaryKey(autoGenerate = false)
    val carName: String,
)
