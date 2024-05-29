package com.example.thecars.domain.usecases

import com.example.thecars.data.CarEntity
import com.example.thecars.repositories.CarsRepository

class RemoveCarFromDatabaseUseCase(private val repository: CarsRepository) {

    suspend fun removeCar(car: CarEntity) {
        repository.removeCarFromDatabase(car)
    }
}