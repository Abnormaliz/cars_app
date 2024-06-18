package com.example.thecars.domain.usecases

import com.example.thecars.data.CarEntity

interface AddCarToDatabaseUseCase {
    suspend fun addCar(car: CarEntity)
}