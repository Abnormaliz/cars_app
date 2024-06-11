package com.example.thecars.domain.models.classes

data class Car(
    val brand: String,
    val model: String,
    val name: String,
    val previewPhoto: Int = 0,
    val frontPhoto: Int = 0,
    val backPhoto: Int = 0,
    val sidePhoto: Int = 0
)