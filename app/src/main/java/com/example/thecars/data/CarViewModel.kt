package com.example.thecars.data

import androidx.lifecycle.ViewModel
import com.example.thecars.R
import com.example.thecars.classes.Car

class CarViewModel : ViewModel() {
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