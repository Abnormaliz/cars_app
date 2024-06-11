package com.example.thecars.presentation.interfaces

import com.example.thecars.domain.models.classes.Brand
import com.example.thecars.presentation.models.BrandUi

interface OnBrandClickListener {
    fun onBrandClick(brand: BrandUi)
}

