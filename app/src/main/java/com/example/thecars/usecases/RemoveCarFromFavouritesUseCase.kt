package com.example.thecars.usecases

import com.example.thecars.data.CarEntity
import com.example.thecars.repositories.CarsRepository

class RemoveCarFromFavouritesUseCase(private val repository: CarsRepository) {

    suspend fun removeCars(cars: List<CarEntity>) {
        repository.removeCarsFromFavourites(cars)
    }

}
