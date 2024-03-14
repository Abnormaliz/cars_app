package com.example.thecars.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "list_dates")
data class NameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val previewPhoto: Int = 0
)
