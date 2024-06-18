package com.example.thecars.domain.usecases

import com.example.thecars.data.CarEntity

interface RemoveCarFromDatabaseUseCase {

    suspend fun removeCar(car: CarEntity)

}