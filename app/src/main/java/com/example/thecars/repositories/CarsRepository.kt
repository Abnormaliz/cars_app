package com.example.thecars.repositories

import com.example.thecars.data.CarEntity
import com.example.thecars.data.MainDb
import kotlinx.coroutines.flow.Flow

class CarsRepository(private val db: MainDb) {

    fun getAllCars(): Flow<List<CarEntity>> = db.dao.getAllCars()

    suspend fun removeCarsFromFavourites(cars: List<CarEntity>) {
        db.dao.deleteCarsFromFavourites(cars)
    }

    //TODO остальные методы

}
