package com.example.thecars.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(nameEntity: NameEntity)
    @Delete
    suspend fun deleteItem(nameEntity: MutableList<NameEntity>)
    @Query("SELECT * FROM list_dates")
    fun getAllItems(): LiveData<List<NameEntity>>
    @Query("SELECT * FROM list_dates WHERE name = :name")
    suspend fun getItemByName(name: String): NameEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteItem(notesEntity: NotesEntity)

//    @Query ("UPDATE list_notes SET text = :newNoteTest WHERE id = :id")
//    suspend fun updateNote(id: Int, newNoteTest: String)
    @Delete
    suspend fun deleteNoteItem(notesEntity: NotesEntity)
    @Query("SELECT * FROM list_notes")
    fun getAllNoteItems(): LiveData<NotesEntity>

    @Query("SELECT * FROM list_notes WHERE carName = :carName")
    suspend fun getNoteByName(carName: String): NotesEntity?

}