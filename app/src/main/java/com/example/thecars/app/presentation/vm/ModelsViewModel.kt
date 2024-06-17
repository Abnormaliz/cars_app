package com.example.thecars.app.presentation.vm

import androidx.lifecycle.ViewModel
import com.example.thecars.app.presentation.models.BrandUi
import com.example.thecars.app.presentation.models.ModelUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ModelsViewModel(brand: BrandUi) : ViewModel() {

    private val _modelList = MutableStateFlow(brand.modelList)
    val modelList: StateFlow<List<ModelUi>> = _modelList.asStateFlow()

    val brandName = brand.name

}
