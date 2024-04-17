package com.example.thecars.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "list_dates")
data class NameEntity(
    val brand: String,
    val model: String,
    val name: String,
    val previewPhoto: Int = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
