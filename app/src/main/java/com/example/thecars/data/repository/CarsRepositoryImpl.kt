package com.example.thecars.data.repository

import com.example.thecars.data.CarEntity
import com.example.thecars.data.MainDb
import com.example.thecars.data.NotesEntity
import com.example.thecars.domain.repositories.CarsRepository
import kotlinx.coroutines.flow.Flow

class CarsRepositoryImpl(private val db: MainDb) : CarsRepository {

    override fun getAllCars(): Flow<List<CarEntity>> = db.dao.getAllCars()

    override fun checkCar(carName: String): Flow<Boolean> = db.dao.checkCar(carName)

    override fun getNoteByName(carName: String): Flow<NotesEntity?> = db.dao.getNoteByName(carName)


    override suspend fun removeCarsFromFavourites(cars: List<CarEntity>) {
        db.dao.deleteCarsFromFavourites(cars)
    }

    override suspend fun removeCarFromDatabase(car: CarEntity) {
        db.dao.deleteOneItem(car)
    }

    override suspend fun addCarToDatabase(car: CarEntity) {
        db.dao.insertItem(car)
    }

    override suspend fun removeNoteFromDatabase(note: NotesEntity) {
        db.dao.deleteNoteItem(note)
    }

    override suspend fun addNoteToDatabase(note: NotesEntity) {
        db.dao.insertNoteItem(note)
    }


}
