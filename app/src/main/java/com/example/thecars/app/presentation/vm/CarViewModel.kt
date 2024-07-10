package com.example.thecars.app.presentation.vm

import androidx.lifecycle.ViewModel
import com.example.thecars.app.presentation.models.CarUi
import com.example.thecars.app.presentation.models.ModelUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CarViewModel(model: ModelUi) : ViewModel() {

    private val _currentCars = MutableStateFlow(model.list)
    val currentCars: StateFlow<List<CarUi>> = _currentCars.asStateFlow()

    val modelName = model.brand + " " + model.model

}