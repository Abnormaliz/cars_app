package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thecars.App
import com.example.thecars.classes.Car
import com.example.thecars.data.MainDb

class CarDetailsViewModel(val database: MainDb) : ViewModel() {

    private val _currentImageList = MutableLiveData<List<Int>>()
    val currentImageList: LiveData<List<Int>>
        get() = _currentImageList
    fun setCurrentCar(car: Car) {
        _currentImageList.value = listOf(car.frontPhoto, car.backPhoto, car.sidePhoto)
    }
    companion object {
        class CarDetailsViewModelFactory(private val app: App): ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CarDetailsViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return CarDetailsViewModel(app.database) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}