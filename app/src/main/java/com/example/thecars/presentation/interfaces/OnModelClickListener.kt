package com.example.thecars.presentation.interfaces

import com.example.thecars.domain.models.classes.Model
import com.example.thecars.presentation.models.ModelUi

interface OnModelClickListener {
    fun onModelClick(model: ModelUi)
}