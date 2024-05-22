package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Brand
import com.example.thecars.lists.allBrandsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BrandsViewModel : ViewModel() {

    private val _carList = MutableStateFlow<List<Brand>>(emptyList())
    val carList: StateFlow<List<Brand>> = _carList.asStateFlow()

    init {
        _carList.value = allBrandsList
    }
}