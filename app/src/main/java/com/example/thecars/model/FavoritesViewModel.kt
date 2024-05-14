package com.example.thecars.model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.thecars.App
import com.example.thecars.classes.Car
import com.example.thecars.data.MainDb
import com.example.thecars.data.CarEntity
import com.example.thecars.lists.allBrandsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(val database: MainDb) : ViewModel() {

    val dataList = database.dao.getAllItems()

    fun removeItem(item: MutableList<CarEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            database.dao.deleteItem(item)
        }
    }
    fun getCarFromCarEntity(selectedCar: CarEntity): Car? {
        val carList = allBrandsList.find { it.name == selectedCar.brand }
        val modelList = carList?.modelList
        return modelList?.find { it.name == selectedCar.model }?.list?.find { it.name == selectedCar.carName }
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
