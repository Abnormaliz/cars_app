package com.example.thecars.app.presentation.interfaces

import com.example.thecars.data.CarEntity

interface OnItemClickListener {
    fun onItemClick(position: CarEntity)
}