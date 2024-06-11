package com.example.thecars.data.usecase

import com.example.thecars.data.CarEntity
import com.example.thecars.data.repository.CarsRepositoryImpl
import com.example.thecars.domain.usecases.RemoveCarFromDatabaseUseCase

class RemoveCarFromDatabaseUseCaseImpl(private val repository: CarsRepositoryImpl) :
    RemoveCarFromDatabaseUseCase {

    override suspend fun removeCar(car: CarEntity) {
        repository.removeCarFromDatabase(car)
    }
}