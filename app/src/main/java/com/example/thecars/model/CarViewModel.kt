package com.example.thecars.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.R
import com.example.thecars.classes.Car

class CarViewModel(application: Application) : AndroidViewModel(application) {

    private val _carNames = MutableLiveData<Array<String>>()
    val carNames: LiveData<Array<String>>
        get() = _carNames

    private val _carList = MutableLiveData<List<Car>>()
    val carList: LiveData<List<Car>>
        get() = _carList

    init {
        _carNames.value = application.resources.getStringArray(R.array.car_names)
        updateCarList()
    }


    private fun updateCarList() {
        _carNames.value?.let { carNames ->
            val cars = carNames.map {
                val logo = when (it) {
                    "Audi" -> R.drawable.audi
                    "BMW" -> R.drawable.bmw
                    "Mercedes-benz" -> R.drawable.mb
                    "Toyota" -> R.drawable.toyota
                    "Volkswagen" -> R.drawable.volkswagen
                    "Volvo" -> R.drawable.volvo
                    else -> R.drawable.unknown
                }
                Car(logo, it)
            }
            _carList.value = cars
        }

    }
}