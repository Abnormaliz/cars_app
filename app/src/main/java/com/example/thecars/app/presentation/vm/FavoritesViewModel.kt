package com.example.thecars.app.presentation.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecars.app.presentation.models.CarUi
import com.example.thecars.app.presentation.models.toBrandUi
import com.example.thecars.app.presentation.models.toCar
import com.example.thecars.data.classes.Car
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

    fun removeCars(items: List<CarUi>) {
        viewModelScope.launch(Dispatchers.IO) {
            removeCarFromFavouritesUseCase.removeCars(items.map { it.toCar() })
        }
    }
}
