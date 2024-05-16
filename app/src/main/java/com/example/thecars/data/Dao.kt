package com.example.thecars.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.thecars.classes.Car
import com.example.thecars.lists.allBrandsList


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(carEntity: CarEntity)
    @Delete
    suspend fun deleteItem(carEntity: MutableList<CarEntity>)
    @Delete
    suspend fun deleteOneItem(carEntity: CarEntity)
    @Query("SELECT * FROM list_dates")
    fun getAllItems(): LiveData<List<CarEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteItem(notesEntity: NotesEntity)

    @Delete
    suspend fun deleteNoteItem(notesEntity: NotesEntity)

    @Query("SELECT * FROM list_notes WHERE carName = :carName")
    fun getNoteByName(carName: String): LiveData<NotesEntity?>

    @Query("SELECT EXISTS(SELECT 1 FROM list_dates WHERE carName = :carName LIMIT 1)")
    fun doesCarExist(carName: String): LiveData<Boolean>
}
