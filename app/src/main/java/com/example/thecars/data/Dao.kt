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

}