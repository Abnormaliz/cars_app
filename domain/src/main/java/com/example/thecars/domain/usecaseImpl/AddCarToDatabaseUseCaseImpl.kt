package com.example.thecars.domain.usecaseImpl


import com.example.thecars.data.classes.Car
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecases.AddCarToDatabaseUseCase

class AddCarToDatabaseUseCaseImpl(private val repository: CarsRepositoryImpl) : AddCarToDatabaseUseCase {

    override suspend fun addCar(car: Car) {
        repository.addCarToDatabase(car)
    }
}