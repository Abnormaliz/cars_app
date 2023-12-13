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
    init {
        _carNames.value = application.resources.getStringArray(R.array.car_names)
    }


    fun getCarList(carNames: Array<String>): List<Car> {
        return carNames.map {
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
    }
}