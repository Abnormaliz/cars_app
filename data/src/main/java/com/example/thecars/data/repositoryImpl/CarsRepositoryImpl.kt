package com.example.thecars.data.repositoryImpl

import com.example.thecars.data.CarDatabase
import com.example.thecars.data.Note
import com.example.thecars.data.classes.Car
import com.example.thecars.data.classes.RemoteCar
import com.example.thecars.data.remote.CarApi
import com.example.thecars.data.repositories.CarsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit

class CarsRepositoryImpl(private val db: CarDatabase, private val carApi: CarApi) : CarsRepository {

    override fun getAllCars(): Flow<List<Car>> = db.dao.getAllCars()

    override fun checkCar(carName: String): Flow<Boolean> = db.dao.checkCar(carName)

    override fun getNoteByName(carName: String): Flow<Note?> = db.dao.getNoteByName(carName)

    override suspend fun getCarApi(
        limit: String,
        page: String,
        type: String?,
        model: String?,
        make: String?
    ): List<RemoteCar> = carApi.getCar(limit, page)


    override suspend fun removeCarsFromFavourites(cars: List<Car>) {
        db.dao.deleteCarsFromFavourites(cars)
    }

    override suspend fun removeCarFromDatabase(car: Car) {
        db.dao.deleteOneItem(car)
    }

    override suspend fun addCarToDatabase(car: Car) {
        db.dao.insertItem(car)
    }

    override suspend fun removeNoteFromDatabase(note: Note) {
        db.dao.deleteNoteItem(note)
    }

    override suspend fun addNoteToDatabase(note: Note) {
        db.dao.insertNoteItem(note)
    }


}
