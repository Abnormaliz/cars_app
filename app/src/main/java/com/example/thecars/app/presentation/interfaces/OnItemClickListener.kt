package com.example.thecars.app.presentation.interfaces

import com.example.thecars.app.presentation.models.CarUi

interface OnItemClickListener {
    fun onItemClick(car: CarUi)
}