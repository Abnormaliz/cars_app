package com.example.thecars.interfaces

import com.example.thecars.data.CarEntity

interface OnItemClickListener {
    fun onItemClick(position: CarEntity)
}