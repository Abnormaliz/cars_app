package com.example.thecars.domain.models.classes


data class Brand(
    val logo: Int,
    val name: String,
    val modelList: List<Model>
)
