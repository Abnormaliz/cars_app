package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.CarEntity
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecases.ObserveAllCarsUseCase
import kotlinx.coroutines.flow.Flow

class ObserveAllCarsUseCaseImpl(private val repository: CarsRepositoryImpl) :
    ObserveAllCarsUseCase {

    override fun observeCars(): Flow<List<CarEntity>> = repository.getAllCars()

}
