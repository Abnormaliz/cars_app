package com.example.thecars.presentation.interfaces

import com.example.thecars.data.CarEntity

interface OnItemClickListener {
    fun onItemClick(position: CarEntity)
}