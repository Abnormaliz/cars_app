package com.example.thecars.app.presentation.interfaces

import com.example.thecars.app.presentation.models.CarUi

interface OnCarClickListener {
    fun onCarClick(car: CarUi)
}