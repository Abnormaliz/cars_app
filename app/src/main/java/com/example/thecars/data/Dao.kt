package com.example.thecars.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(carEntity: CarEntity)
    @Delete
    suspend fun deleteCarsFromFavourites(carEntity: List<CarEntity>)
    @Delete
    suspend fun deleteOneItem(carEntity: CarEntity)
    @Query("SELECT * FROM list_dates")
    fun getAllCars(): Flow<List<CarEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteItem(notesEntity: NotesEntity)

    @Delete
    suspend fun deleteNoteItem(notesEntity: NotesEntity)

    @Query("SELECT * FROM list_notes WHERE carName = :carName")
    fun getNoteByName(carName: String): Flow<NotesEntity?>

    @Query("SELECT EXISTS(SELECT 1 FROM list_dates WHERE carName = :carName LIMIT 1)")
    fun checkCar(carName: String): Flow<Boolean>
}
