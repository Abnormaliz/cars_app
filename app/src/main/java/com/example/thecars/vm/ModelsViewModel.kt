package com.example.thecars.vm

import androidx.lifecycle.ViewModel
import com.example.thecars.domain.models.classes.Brand
import com.example.thecars.domain.models.classes.Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ModelsViewModel(brand: Brand) : ViewModel() {

    private val _modelList = MutableStateFlow(brand.modelList)
    val modelList: StateFlow<List<Model>> = _modelList.asStateFlow()

    val brandName = brand.name

}
