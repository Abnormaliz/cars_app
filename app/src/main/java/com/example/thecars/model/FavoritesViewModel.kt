package com.example.thecars.model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecars.classes.Car
import com.example.thecars.data.CarEntity
import com.example.thecars.data.MainDb
import com.example.thecars.lists.allBrandsList
import com.example.thecars.usecases.ObserveAllCarsUseCase
import com.example.thecars.usecases.RemoveCarFromFavouritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(
    observeAllCarsUseCase: ObserveAllCarsUseCase,
    private val removeCarFromFavouritesUseCase: RemoveCarFromFavouritesUseCase,
) : ViewModel() {

    val cars = observeAllCarsUseCase.observeCars()

    fun removeCars(items: List<CarEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            removeCarFromFavouritesUseCase.removeCars(items)
        }
    }

    fun getCarFromCarEntity(selectedCar: CarEntity): Car? {
        val carList = allBrandsList.find { it.name == selectedCar.brand }
        val modelList = carList?.modelList
        return modelList?.find { it.name == selectedCar.model }?.list?.find { it.name == selectedCar.carName }
    }
}
