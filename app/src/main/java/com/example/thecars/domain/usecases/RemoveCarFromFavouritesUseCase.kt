package com.example.thecars.domain.usecases

import com.example.thecars.data.CarEntity
import com.example.thecars.data.repository.CarsRepositoryImpl

class RemoveCarFromFavouritesUseCase(private val repository: CarsRepositoryImpl) {

    suspend fun removeCars(cars: List<CarEntity>) {
        repository.removeCarsFromFavourites(cars)
    }

}
