package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Date
import com.example.thecars.lists.carsDates
import com.example.thecars.lists.carsImages

class DateViewModel : ViewModel() {

    private val _currentDate = MutableLiveData<List<Date>>()
    val currentDate: LiveData<List<Date>>
        get() = _currentDate
    var currentBrand: String = ""
    fun setCurrentDate(model: String, brand: String) {
        currentBrand = brand
        val carsDates = carsDates[brand]
        val carsImages = carsImages[brand]
        val currentDate = carsDates?.get(model)!!
        val currentImage = carsImages?.get(model)!!
        val dates = mutableListOf<Date>()
        for (i in currentDate.indices) {
            dates.add(
                Date(
                    title = currentDate[i],
                    imageId = currentImage[i]
                )
            )
        }
        _currentDate.value = dates
    }
}