package com.example.thecars.domain.usecases

import com.example.thecars.data.classes.Car

interface RemoveCarFromFavouritesUseCase {

    suspend fun removeCars(cars: List<Car>)

}