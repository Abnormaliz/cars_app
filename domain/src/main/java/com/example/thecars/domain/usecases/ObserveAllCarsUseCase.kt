package com.example.thecars.domain.usecases

import com.example.thecars.data.classes.Car
import kotlinx.coroutines.flow.Flow

interface ObserveAllCarsUseCase {

    fun observeCars(): Flow<List<Car>>

}