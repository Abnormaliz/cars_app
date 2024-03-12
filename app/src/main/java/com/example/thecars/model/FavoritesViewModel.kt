package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecars.classes.Date

class FavoritesViewModel : ViewModel() {

    private val _currentFavorites = MutableLiveData<List<Date>>()
    val currentFavorites: LiveData<List<Date>>
        get() = _currentFavorites

    fun addFavorites(favorites: Date) {
        val currentList = _currentFavorites.value ?: listOf()
        _currentFavorites.value = currentList + favorites
    }
}