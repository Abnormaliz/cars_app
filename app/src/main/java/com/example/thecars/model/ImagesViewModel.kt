package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.lists.datesToImages

class ImagesViewModel : ViewModel() {
    private val _currentDate = MutableLiveData<String>()
    val currentDate: LiveData<String>
        get() = _currentDate

    fun setCurrentDate(date: String){
        _currentDate.value = date
    }
    fun setOneDate(): List<Int> {
        return datesToImages[currentDate.value]!!
    }
}