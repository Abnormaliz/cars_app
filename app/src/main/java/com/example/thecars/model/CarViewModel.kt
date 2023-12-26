package com.example.thecars.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.R
import com.example.thecars.classes.Car
import com.example.thecars.lists.allCarsList

class CarViewModel() : ViewModel() {

    private val _carList = MutableLiveData<List<Car>>()
    val carList: LiveData<List<Car>>
        get() = _carList

    init {
        _carList.value = allCarsList
    }
}