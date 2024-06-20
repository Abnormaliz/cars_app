package com.example.thecars.domain.usecases

import com.example.thecars.data.classes.Car

interface RemoveCarFromDatabaseUseCase {

    suspend fun removeCar(car: Car)

}