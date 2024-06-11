package com.example.thecars.data.usecase

import com.example.thecars.data.CarEntity
import com.example.thecars.data.repository.CarsRepositoryImpl
import com.example.thecars.domain.usecases.ObserveAllCarsUseCase
import kotlinx.coroutines.flow.Flow

class ObserveAllCarsUseCaseImpl(private val repository: CarsRepositoryImpl) :
    ObserveAllCarsUseCase {

    override fun observeCars(): Flow<List<CarEntity>> = repository.getAllCars()

}
