package com.example.thecars.data.usecase

import com.example.thecars.data.CarEntity
import com.example.thecars.data.repository.CarsRepositoryImpl
import com.example.thecars.domain.usecases.AddCarToDatabaseUseCase

class AddCarToDatabaseUseCaseImpl(private val repository: CarsRepositoryImpl) :
    AddCarToDatabaseUseCase {

    override suspend fun addCar(car: CarEntity) {
        repository.addCarToDatabase(car)
    }
}