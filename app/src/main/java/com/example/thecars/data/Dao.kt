package com.example.thecars.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(carEntity: CarEntity)
    @Delete
    suspend fun deleteItem(carEntity: MutableList<CarEntity>)
    @Query("SELECT * FROM list_dates")
    fun getAllItems(): LiveData<List<CarAndNote>>
    @Query("SELECT * FROM list_dates WHERE carName = :name")
    suspend fun getItemByName(name: String): CarEntity?

    @Query("SELECT * FROM list_dates WHERE carName = :name")
    fun getItemId(name: String): CarEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteItem(notesEntity: NotesEntity)

    @Delete
    suspend fun deleteNoteItem(notesEntity: NotesEntity)
    @Query("SELECT * FROM list_notes")
    fun getAllNoteItems(): LiveData<NotesEntity>

    @Query("SELECT * FROM list_notes WHERE carName = :carName")
    suspend fun getNoteByName(carName: String): NotesEntity?

    @Transaction
    suspend fun deleteCarWithNotes(carEntities: MutableList<CarEntity>) {
        carEntities.forEach { car ->
            deleteNotesByCarName(car.carName)
        }
        deleteCars(carEntities)
    }


    @Query("DELETE FROM list_notes WHERE carName = :carName")
    suspend fun deleteNotesByCarName(carName: String)

    @Delete
    suspend fun deleteCars(carEntities: MutableList<CarEntity>)
}
