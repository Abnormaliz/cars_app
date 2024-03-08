package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.lists.carsDateToImages

class ImagesViewModel : ViewModel() {

    private val _currentImageList = MutableLiveData<List<Int>>()
    val currentImageList: LiveData<List<Int>>
        get() = _currentImageList

    fun setCurrentDate(date: String, brand: String){
        val currentBrand = carsDateToImages[brand]
        _currentImageList.value = currentBrand?.get(date)
    }
}