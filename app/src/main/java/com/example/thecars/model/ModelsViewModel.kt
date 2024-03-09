package com.example.thecars.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Car

class ModelsViewModel() : ViewModel() {

    private val _modelList = MutableLiveData<List<String>>()
    val modelList: LiveData<List<String>>
        get() = _modelList

    fun setModelList(car: Car) {
        _modelList.value = car.modelList
    }
}
