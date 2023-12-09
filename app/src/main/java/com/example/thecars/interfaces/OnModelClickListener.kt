package com.example.thecars.interfaces

import com.example.thecars.classes.Car
import com.example.thecars.classes.Model

interface OnModelClickListener {
    fun onModelClick(model: List<String>)
}