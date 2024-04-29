package com.example.thecars.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [
        CarEntity::class,
        NotesEntity::class,
    ],
    version = 1
)
abstract class MainDb : RoomDatabase() {

    abstract val dao: Dao
companion object{
    fun createDatabase(context: Context) : MainDb{
        return Room.databaseBuilder(
            context,
            MainDb::class.java,
            "test.db"
        ).build()
    }
}
}