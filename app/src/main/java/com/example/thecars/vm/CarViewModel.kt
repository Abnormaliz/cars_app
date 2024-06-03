package com.example.thecars.vm

import androidx.lifecycle.ViewModel
import com.example.thecars.domain.models.classes.Car
import com.example.thecars.domain.models.classes.Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CarViewModel(val model: Model) : ViewModel() {

    private val _currentCars = MutableStateFlow(model.list)
    val currentCars: StateFlow<List<Car>> = _currentCars.asStateFlow()

    val modelName = model.name

}