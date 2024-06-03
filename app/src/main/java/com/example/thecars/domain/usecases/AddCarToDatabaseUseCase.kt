package com.example.thecars.domain.usecases

import com.example.thecars.data.CarEntity
import com.example.thecars.data.repository.CarsRepositoryImpl

class AddCarToDatabaseUseCase(private val repository: CarsRepositoryImpl) {

    suspend fun addCar(car: CarEntity) {
        repository.addCarToDatabase(car)
    }
}