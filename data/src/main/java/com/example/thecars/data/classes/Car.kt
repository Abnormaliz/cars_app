package com.example.thecars.data.classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_cars")
data class Car(
    val brand: String,
    val model: String,
    @PrimaryKey (autoGenerate = false)
    val car: String,
    val release: String,
    val previewPhoto: Int = 0,
    val frontPhoto: Int = 0,
    val backPhoto: Int = 0,
    val sidePhoto: Int = 0
)