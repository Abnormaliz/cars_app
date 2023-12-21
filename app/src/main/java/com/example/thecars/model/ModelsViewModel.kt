package com.example.thecars.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thecars.lists.brandToModels

class ModelsViewModel(application: Application) : AndroidViewModel(application) {

    private val _currentBrand = MutableLiveData<String>()
    private val currentBrand: LiveData<String>
        get() = _currentBrand

    private val _modelList = MutableLiveData<List<String>>()
    val modelList: LiveData<List<String>>
        get() = _modelList
    fun setCurrentBrand(brand: String) {
    _currentBrand.value = brand
        val modelArray = brandToModels[currentBrand.value]
        val models = modelArray.let {
            getApplication<Application>().resources.getStringArray(modelArray!!).toList()
        }
        _modelList.value = models
    }
}
