package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.classes.Car
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecases.RemoveCarFromDatabaseUseCase

class RemoveCarFromDatabaseUseCaseImpl(private val repository: CarsRepositoryImpl) : RemoveCarFromDatabaseUseCase {

    override suspend fun removeCar(car: Car) {
        repository.removeCarFromDatabase(car)
    }
}