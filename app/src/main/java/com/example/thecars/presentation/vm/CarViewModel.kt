package com.example.thecars.presentation.vm

import androidx.lifecycle.ViewModel
import com.example.thecars.presentation.models.CarUi
import com.example.thecars.presentation.models.ModelUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CarViewModel(val model: ModelUi) : ViewModel() {

    private val _currentCars = MutableStateFlow(model.list)
    val currentCars: StateFlow<List<CarUi>> = _currentCars.asStateFlow()

    val modelName = model.name

}