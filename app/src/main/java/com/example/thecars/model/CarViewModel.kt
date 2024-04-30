package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Car
import com.example.thecars.classes.Model

class CarViewModel : ViewModel() {

    private val _currentCars = MutableLiveData<List<Car>>()
    val currentCars: LiveData<List<Car>>
        get() = _currentCars
    fun setCurrentCars(model: Model) {
        _currentCars.value = model.list
    }
}