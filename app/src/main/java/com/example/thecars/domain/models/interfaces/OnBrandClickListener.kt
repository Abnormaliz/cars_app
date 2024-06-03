package com.example.thecars.domain.models.interfaces

import com.example.thecars.domain.models.classes.Brand

interface OnBrandClickListener {
    fun onBrandClick(brand: Brand)
}

