package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Brand
import com.example.thecars.lists.allBrandsList

class BrandsViewModel() : ViewModel() {

    private val _carList = MutableLiveData<List<Brand>>()
    val carList: LiveData<List<Brand>>
        get() = _carList

    init {
        _carList.value = allBrandsList
    }
}