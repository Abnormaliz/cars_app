package com.example.thecars.domain.usecases

import com.example.thecars.data.classes.RemoteCar

interface GetCarFromApiUseCase {
    suspend fun getCar(): List<RemoteCar>

}