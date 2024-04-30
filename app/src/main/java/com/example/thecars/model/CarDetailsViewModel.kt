package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Car

class CarDetailsViewModel : ViewModel() {

    private val _currentImageList = MutableLiveData<List<Int>>()
    val currentImageList: LiveData<List<Int>>
        get() = _currentImageList
    fun setCurrentCar(car: Car) {
        _currentImageList.value = listOf(car.frontPhoto, car.backPhoto, car.sidePhoto)
    }
}