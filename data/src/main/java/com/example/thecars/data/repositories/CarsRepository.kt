package com.example.thecars.data.repositories

import com.example.thecars.data.Note
import com.example.thecars.data.classes.Car
import com.example.thecars.data.classes.RemoteCar
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    fun getAllCars(): Flow<List<Car>>

    fun checkCar(carName: String): Flow<Boolean>

    fun getNoteByName(carName: String): Flow<Note?>

    suspend fun getCarApi(
        limit: String,
        page: String,
        type: String?,
        model: String?,
        make: String?
    ): List<RemoteCar>

    suspend fun removeCarsFromFavourites(cars: List<Car>)

    suspend fun removeCarFromDatabase(car: Car)

    suspend fun addCarToDatabase(car: Car)

    suspend fun removeNoteFromDatabase(note: Note)

    suspend fun addNoteToDatabase(note: Note)


}