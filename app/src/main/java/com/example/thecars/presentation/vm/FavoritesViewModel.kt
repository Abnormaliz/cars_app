package com.example.thecars.presentation.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecars.domain.models.classes.Car
import com.example.thecars.data.CarEntity
import com.example.thecars.domain.models.lists.allBrandsList
import com.example.thecars.domain.usecases.ObserveAllCarsUseCase
import com.example.thecars.domain.usecases.RemoveCarFromFavouritesUseCase
import com.example.thecars.presentation.models.CarUi
import com.example.thecars.presentation.models.toBrandUi
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

    fun getCarFromCarEntity(selectedCar: CarEntity): CarUi? {
        val carList = allBrandsList.map { brand -> brand.toBrandUi() }.find { it.name == selectedCar.brand }
        val modelList = carList?.modelList
        return modelList?.find { it.name == selectedCar.model }?.list?.find { it.name == selectedCar.carName }
    }
}
