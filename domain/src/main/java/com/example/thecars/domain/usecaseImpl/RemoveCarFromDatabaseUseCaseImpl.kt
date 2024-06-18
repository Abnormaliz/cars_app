package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.CarEntity
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecases.RemoveCarFromDatabaseUseCase

class RemoveCarFromDatabaseUseCaseImpl(private val repository: CarsRepositoryImpl) : RemoveCarFromDatabaseUseCase {

    override suspend fun removeCar(car: CarEntity) {
        repository.removeCarFromDatabase(car)
    }
}