package com.example.thecars.domain.usecases

import com.example.thecars.data.CarEntity
import com.example.thecars.data.repository.CarsRepositoryImpl
import kotlinx.coroutines.flow.Flow

class ObserveAllCarsUseCase(private val repository: CarsRepositoryImpl) {

    fun observeCars(): Flow<List<CarEntity>> = repository.getAllCars()

}
