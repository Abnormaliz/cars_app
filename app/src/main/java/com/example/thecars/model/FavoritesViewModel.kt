package com.example.thecars.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.thecars.App
import com.example.thecars.data.MainDb
import com.example.thecars.data.CarEntity
import kotlinx.coroutines.launch

class FavoritesViewModel(val database: MainDb) : ViewModel() {

    private val carsWithNotes = database.dao.getAllItems()
    val dataList = carsWithNotes.map { carsAndNotes -> carsAndNotes.map { it.car } }

    fun removeItem(item: MutableList<CarEntity>) = viewModelScope.launch {
        database.dao.deleteItem(item)
    }
}

class FavoritesViewModelFactory(private val app: App) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoritesViewModel(app.database) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }