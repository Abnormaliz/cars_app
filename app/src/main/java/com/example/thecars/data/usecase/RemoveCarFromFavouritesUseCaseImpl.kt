package com.example.thecars.data.usecase

import com.example.thecars.data.CarEntity
import com.example.thecars.data.repository.CarsRepositoryImpl
import com.example.thecars.domain.usecases.RemoveCarFromFavouritesUseCase

class RemoveCarFromFavouritesUseCaseImpl(private val repository: CarsRepositoryImpl) :
    RemoveCarFromFavouritesUseCase {

    override suspend fun removeCars(cars: List<CarEntity>) {
        repository.removeCarsFromFavourites(cars)
    }

}
