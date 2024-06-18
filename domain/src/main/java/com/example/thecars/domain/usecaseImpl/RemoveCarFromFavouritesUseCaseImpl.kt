package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.CarEntity
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecases.RemoveCarFromFavouritesUseCase

class RemoveCarFromFavouritesUseCaseImpl(private val repository: CarsRepositoryImpl) : RemoveCarFromFavouritesUseCase {

    override suspend fun removeCars(cars: List<CarEntity>) {
        repository.removeCarsFromFavourites(cars)
    }

}
