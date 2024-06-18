package com.example.thecars.domain.usecases

import kotlinx.coroutines.flow.Flow

interface CheckCarUseCase {

    fun checkCar(carName: String) : Flow<Boolean>

}