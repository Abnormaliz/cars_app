package com.example.thecars.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thecars.lists.brandToModels

class ModelsViewModel(application: Application) : AndroidViewModel(application) {

    private val _currentBrand = MutableLiveData<String>()
    val currentBrand: LiveData<String>
        get() = _currentBrand
    fun setCurrentBrand(brand: String) {
    _currentBrand.value = brand
}
    fun setListModel(): List<String> {
        val modelArray = brandToModels[currentBrand.value]
        return getApplication<Application>().resources.getStringArray(modelArray!!).toList()
    }


}
