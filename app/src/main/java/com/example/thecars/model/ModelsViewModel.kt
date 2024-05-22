package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thecars.classes.Brand
import com.example.thecars.classes.Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ModelsViewModel(brand: Brand) : ViewModel() {

    private val _modelList = MutableStateFlow(brand.modelList)
    val modelList: StateFlow<List<Model>> = _modelList.asStateFlow()

    val brandName = brand.name
    companion object {
        class ModelViewModelFactory(private val brand: Brand) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ModelsViewModel::class.java)) {
                    return ModelsViewModel(brand) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }

        }
    }
}
