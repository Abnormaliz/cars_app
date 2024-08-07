package com.example.thecars.domain.usecases

import com.example.thecars.data.classes.RemoteCar

interface GetCarFromApiUseCase {
    suspend fun getCar(
        limit: String,
        page: String,
        type: String?,
        model: String?,
        make: String?,
        year: String?
    ): List<RemoteCar>

}