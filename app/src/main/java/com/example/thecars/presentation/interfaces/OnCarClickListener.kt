package com.example.thecars.presentation.interfaces

import com.example.thecars.domain.models.classes.Car
import com.example.thecars.presentation.models.CarUi

interface OnCarClickListener {
    fun onCarClick(car: CarUi)
}