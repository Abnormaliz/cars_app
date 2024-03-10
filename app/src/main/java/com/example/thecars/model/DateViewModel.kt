package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Date
import com.example.thecars.classes.Model

class DateViewModel : ViewModel() {

    private val _currentDate = MutableLiveData<List<Date>>()
    val currentDate: LiveData<List<Date>>
        get() = _currentDate
    var selectedModel: Model? = null
    fun setCurrentDate(model: Model) {
        selectedModel = model
        _currentDate.value = model.list
    }
}