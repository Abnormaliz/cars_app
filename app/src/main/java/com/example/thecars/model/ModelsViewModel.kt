package com.example.thecars.model

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModelsViewModel(application: Application) : AndroidViewModel(application) {

    private val _currentBrand = MutableLiveData<String>()
    val currentBrand: LiveData<String>
        get() = _currentBrand
fun setCurrentBrand(brand: String) {
    _currentBrand.value = brand
}
}
