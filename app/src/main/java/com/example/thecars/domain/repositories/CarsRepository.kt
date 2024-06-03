package com.example.thecars.domain.repositories

import com.example.thecars.data.CarEntity
import com.example.thecars.data.NotesEntity
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    fun getAllCars(): Flow<List<CarEntity>>

    fun checkCar(carName: String): Flow<Boolean>

    fun getNoteByName(carName: String): Flow<NotesEntity?>


    suspend fun removeCarsFromFavourites(cars: List<CarEntity>)

    suspend fun removeCarFromDatabase(car: CarEntity)

    suspend fun addCarToDatabase(car: CarEntity)

    suspend fun removeNoteFromDatabase(note: NotesEntity)

    suspend fun addNoteToDatabase(note: NotesEntity)

}