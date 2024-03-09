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
        val imagelist = mutableListOf<Int>()
        if (date.frontPhoto != 0) {
            imagelist.add(date.frontPhoto)
        }
        if (date.sidePhoto != 0) {
            imagelist.add(date.sidePhoto)
        }
        if (date.backPhoto != 0) {
            imagelist.add(date.backPhoto)
        }
    _currentImageList.value = imagelist
    }
}