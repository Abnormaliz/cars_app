package com.example.thecars.domain.usecases

import com.example.thecars.data.CarEntity
import com.example.thecars.data.repository.CarsRepositoryImpl

class RemoveCarFromDatabaseUseCase(private val repository: CarsRepositoryImpl) {

    suspend fun removeCar(car: CarEntity) {
        repository.removeCarFromDatabase(car)
    }
}