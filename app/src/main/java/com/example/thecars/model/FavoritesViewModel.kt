package com.example.thecars.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.thecars.App
import com.example.thecars.data.MainDb
import com.example.thecars.data.NameEntity
import kotlinx.coroutines.launch
import java.util.jar.Attributes.Name

class FavoritesViewModel(val database: MainDb) : ViewModel() {

    val dataList = database.dao.getAllItems()

    fun removeItem(item: NameEntity) = viewModelScope.launch {
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