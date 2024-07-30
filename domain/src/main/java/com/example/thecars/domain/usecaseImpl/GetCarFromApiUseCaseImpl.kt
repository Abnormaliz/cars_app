package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.classes.RemoteCar
import com.example.thecars.data.remote.CarApi
import com.example.thecars.domain.usecases.GetCarFromApiUseCase

class GetCarFromApiUseCaseImpl(private val carApi: CarApi): GetCarFromApiUseCase {
    override suspend fun getCar(): List<RemoteCar> = carApi.getCar(limit = "10", page = "1")
}