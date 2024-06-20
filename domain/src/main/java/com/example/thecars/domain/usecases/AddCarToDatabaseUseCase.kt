package com.example.thecars.domain.usecases

import com.example.thecars.data.classes.Car

interface AddCarToDatabaseUseCase {
    suspend fun addCar(car: Car)
}