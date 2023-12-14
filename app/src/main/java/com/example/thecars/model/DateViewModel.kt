package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Date
import com.example.thecars.lists.acura_dates
import com.example.thecars.lists.acura_images

class DateViewModel : ViewModel() {

    private val _currentModel = MutableLiveData<String>()
    val currentModel: LiveData<String>
        get() = _currentModel
    fun setCurrentModel(model: String) {
        _currentModel.value = model
    }
    fun getDateList(): List<Date> {
        val currentDate = acura_dates[currentModel.value]!!
        val currentImage = acura_images[currentModel.value]!!
        val dates = mutableListOf<Date>()
        for (i in currentDate.indices) {
            dates.add(
                Date(
                    title = currentDate[i],
                    imageId = currentImage[i]
                )
            )
        }
        return dates
    }
}