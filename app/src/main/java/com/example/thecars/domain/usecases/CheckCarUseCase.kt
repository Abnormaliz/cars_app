package com.example.thecars.domain.usecases

import com.example.thecars.repositories.CarsRepository
import kotlinx.coroutines.flow.Flow

class CheckCarUseCase(private val repository: CarsRepository) {

    fun checkCar(carName: String) : Flow<Boolean> = repository.checkCar(carName)

    }
