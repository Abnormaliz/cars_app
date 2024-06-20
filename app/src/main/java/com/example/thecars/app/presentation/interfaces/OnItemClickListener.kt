package com.example.thecars.app.presentation.interfaces

import com.example.thecars.data.classes.Car

interface OnItemClickListener {
    fun onItemClick(position: Car)
}