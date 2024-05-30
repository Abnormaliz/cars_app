package com.example.thecars.domain.usecases

import com.example.thecars.data.CarEntity
import com.example.thecars.repositories.CarsRepository

class AddCarToDatabaseUseCase(private val repository: CarsRepository) {

    suspend fun addCar(car: CarEntity) {
        repository.addCarToDatabase(car)
    }
}