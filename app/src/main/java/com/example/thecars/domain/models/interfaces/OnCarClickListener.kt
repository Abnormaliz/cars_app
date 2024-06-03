package com.example.thecars.domain.models.interfaces

import com.example.thecars.domain.models.classes.Car

interface OnCarClickListener {
    fun onCarClick(car: Car)
}