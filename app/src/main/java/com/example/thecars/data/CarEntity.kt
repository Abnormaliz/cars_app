package com.example.thecars.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "list_dates")
data class CarEntity(
    val brand: String,
    val model: String,
    @PrimaryKey(autoGenerate = false)
    val carName: String,
    val previewPhoto: Int = 0,
)
