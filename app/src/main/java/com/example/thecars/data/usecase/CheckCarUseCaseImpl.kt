package com.example.thecars.data.usecase

import com.example.thecars.data.repository.CarsRepositoryImpl
import com.example.thecars.domain.usecases.CheckCarUseCase
import kotlinx.coroutines.flow.Flow

class CheckCarUseCaseImpl(private val repository: CarsRepositoryImpl) : CheckCarUseCase {

    override fun checkCar(carName: String) : Flow<Boolean> = repository.checkCar(carName)

    }
