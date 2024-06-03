package com.example.thecars.domain.models.interfaces

import com.example.thecars.data.CarEntity

interface OnItemClickListener {
    fun onItemClick(position: CarEntity)
}