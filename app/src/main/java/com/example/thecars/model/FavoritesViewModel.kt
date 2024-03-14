package com.example.thecars.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.thecars.App
import com.example.thecars.data.MainDb

class FavoritesViewModel(database: MainDb) : ViewModel() {

    val favoriteList = database.dao.getAllItems()
    @Suppress("UNCHECKED_CAST")
    companion object{
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return FavoritesViewModel(database) as T
            }
        }

    }
}