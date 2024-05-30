package com.example.thecars.repositories

import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.thecars.data.CarEntity
import com.example.thecars.data.MainDb
import com.example.thecars.data.NotesEntity
import kotlinx.coroutines.flow.Flow

class CarsRepository(private val db: MainDb) {

    fun getAllCars(): Flow<List<CarEntity>> = db.dao.getAllCars()

    fun checkCar(carName: String): Flow<Boolean> = db.dao.checkCar(carName)

    fun getNoteByName(carName: String): Flow<NotesEntity?> = db.dao.getNoteByName(carName)


    suspend fun removeCarsFromFavourites(cars: List<CarEntity>) {
        db.dao.deleteCarsFromFavourites(cars)
    }

    suspend fun removeCarFromDatabase(car: CarEntity) {
        db.dao.deleteOneItem(car)
    }

    suspend fun addCarToDatabase(car: CarEntity) {
        db.dao.insertItem(car)
    }

    suspend fun removeNoteFromDatabase(note: NotesEntity) {
        db.dao.deleteNoteItem(note)
    }

    suspend fun addNoteToDatabase(note: NotesEntity) {
        db.dao.insertNoteItem(note)
    }


}
