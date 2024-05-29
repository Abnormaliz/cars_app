package com.example.thecars.domain.usecases

import com.example.thecars.data.CarEntity
import com.example.thecars.repositories.CarsRepository
import kotlinx.coroutines.flow.Flow

class ObserveAllCarsUseCase(private val repository: CarsRepository) {

    fun observeCars(): Flow<List<CarEntity>> = repository.getAllCars()

}
