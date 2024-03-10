package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Date

class ImagesViewModel : ViewModel() {

    private val _currentImageList = MutableLiveData<List<Int>>()
    val currentImageList: LiveData<List<Int>>
        get() = _currentImageList

    fun setCurrentDate(date: Date) {
        _currentImageList.value = listOf(date.frontPhoto, date.backPhoto, date.sidePhoto)
    }
}