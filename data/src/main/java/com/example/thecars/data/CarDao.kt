package com.example.thecars.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thecars.data.classes.Car
import kotlinx.coroutines.flow.Flow


@Dao
interface CarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(car: Car)
    @Delete
    suspend fun deleteCarsFromFavourites(car: List<Car>)
    @Delete
    suspend fun deleteOneItem(car: Car)
    @Query("SELECT * FROM list_cars")
    fun getAllCars(): Flow<List<Car>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteItem(notesEntity: Note)

    @Delete
    suspend fun deleteNoteItem(notesEntity: Note)

    @Query("SELECT * FROM list_notes WHERE carName = :carName")
    fun getNoteByName(carName: String): Flow<Note?>

    @Query("SELECT EXISTS(SELECT 1 FROM list_cars WHERE name = :carName LIMIT 1)")
    fun checkCar(carName: String): Flow<Boolean>
}
