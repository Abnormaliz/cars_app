package com.example.thecars.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.R
import com.example.thecars.classes.Car
import com.example.thecars.lists.brandToModels

class CarViewModel : ViewModel() {

    private val _currentBrand = MutableLiveData<String>()
    val currentBrand: LiveData<String> = _currentBrand

    fun setCurrentBrand(brand: String) {
        _currentBrand.value = brand
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
    private fun getModelList(): List<String> {
        val modelArray = brandToModels[currentBrand]
        return resources.getStringArray(modelArray!!).toList()

    }
}