package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Date
import com.example.thecars.objects.FavoritesRepository

class ImagesViewModel : ViewModel() {

    private val _currentImageList = MutableLiveData<List<Int>>()
    val currentImageList: LiveData<List<Int>>
        get() = _currentImageList
    var selectedDate: Date? = null
    fun setCurrentDate(date: Date) {
        selectedDate = date
        _currentImageList.value = listOf(date.frontPhoto, date.backPhoto, date.sidePhoto)
    }

    fun addToFavorites(date: Date) {
        FavoritesRepository.favoritesList.add(date)
    }
}