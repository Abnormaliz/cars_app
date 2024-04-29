package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Brand
import com.example.thecars.classes.Model

class ModelsViewModel() : ViewModel() {

    private val _modelList = MutableLiveData<List<Model>>()
    val modelList: LiveData<List<Model>>
        get() = _modelList

    var selectedCar: Brand? = null
    fun setModelList(car: Brand) {
        selectedCar = car
        _modelList.value = car.modelList
    }
}
