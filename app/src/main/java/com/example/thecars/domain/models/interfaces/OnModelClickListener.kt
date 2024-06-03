package com.example.thecars.domain.models.interfaces

import com.example.thecars.domain.models.classes.Model

interface OnModelClickListener {
    fun onModelClick(model: Model)
}