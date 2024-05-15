package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thecars.App
import com.example.thecars.classes.Car
import com.example.thecars.classes.Model

class CarViewModel(val model: Model) : ViewModel() {

    private val _currentCars = MutableLiveData(model.list)
    val currentCars: LiveData<List<Car>>
        get() = _currentCars

    val modelName = model.name


    companion object {
        class CarViewModelFactory(private val model: Model) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CarViewModel::class.java)) {
                    return CarViewModel(model) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }

        }
    }
}