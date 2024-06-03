package com.example.thecars.domain.usecases

import com.example.thecars.data.repository.CarsRepositoryImpl
import kotlinx.coroutines.flow.Flow

class CheckCarUseCase(private val repository: CarsRepositoryImpl) {

    fun checkCar(carName: String) : Flow<Boolean> = repository.checkCar(carName)

    }
