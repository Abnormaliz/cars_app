package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.classes.RemoteCar
import com.example.thecars.data.remote.CarApi
import com.example.thecars.domain.usecases.GetCarFromApiUseCase

class GetCarFromApiUseCaseImpl(private val carApi: CarApi) : GetCarFromApiUseCase {
    override suspend fun getCar(
        limit: String,
        page: String,
        type: String?,
        model: String?,
        make: String?
    ): List<RemoteCar> {
        return carApi.getCar(limit, page, type, model, make)
    }
}