package com.example.thecars.app.presentation.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecars.app.presentation.models.CarUi
import com.example.thecars.app.presentation.models.toBrandUi
import com.example.thecars.data.CarEntity
import com.example.thecars.data.lists.allBrandsList
import com.example.thecars.domain.usecaseImpl.ObserveAllCarsUseCaseImpl
import com.example.thecars.domain.usecaseImpl.RemoveCarFromFavouritesUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(
    observeAllCarsUseCase: ObserveAllCarsUseCaseImpl,
    private val removeCarFromFavouritesUseCase: RemoveCarFromFavouritesUseCaseImpl,
) : ViewModel() {

    val cars = observeAllCarsUseCase.observeCars()

    fun removeCars(items: List<CarEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            removeCarFromFavouritesUseCase.removeCars(items)
        }
    }

    fun getCarFromCarEntity(selectedCar: com.example.thecars.data.CarEntity): CarUi? {
        val carList = allBrandsList.map { brand -> brand.toBrandUi() }.find { it.name == selectedCar.brand }
        val modelList = carList?.modelList
        return modelList?.find { it.name == selectedCar.model }?.list?.find { it.name == selectedCar.carName }
    }
}
