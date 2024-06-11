package com.example.thecars.presentation.vm

import androidx.lifecycle.ViewModel
import com.example.thecars.domain.models.lists.allBrandsList
import com.example.thecars.presentation.models.BrandUi
import com.example.thecars.presentation.models.toBrandUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BrandsViewModel : ViewModel() {

    private val _carList = MutableStateFlow<List<BrandUi>>(emptyList())
    val carList: StateFlow<List<BrandUi>> = _carList.asStateFlow()

    init {
        _carList.value = allBrandsList.map { it.toBrandUi() }
    }
}