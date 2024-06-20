package com.example.thecars.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thecars.data.classes.Car

@Database(
    entities = [
        Note::class,
        Car::class,
    ],
    version = 1
)
abstract class CarDatabase : RoomDatabase() {
    abstract val dao: CarDao
companion object{
    fun createDatabase(context: Context) : CarDatabase {
        return Room.databaseBuilder(
            context,
            CarDatabase::class.java,
            "car.db"
        ).build()
    }
}
}