package com.example.thecars.domain.usecases

import com.example.thecars.data.CarEntity

interface RemoveCarFromFavouritesUseCase {

    suspend fun removeCars(cars: List<CarEntity>)

}