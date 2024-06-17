package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecases.CheckCarUseCase
import kotlinx.coroutines.flow.Flow

class CheckCarUseCaseImpl(private val repository: CarsRepositoryImpl) : CheckCarUseCase {

    override fun checkCar(carName: String) : Flow<Boolean> = repository.checkCar(carName)

    }
