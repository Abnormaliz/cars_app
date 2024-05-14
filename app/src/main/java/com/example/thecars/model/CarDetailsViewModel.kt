package com.example.thecars.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.thecars.App
import com.example.thecars.classes.Car
import com.example.thecars.data.CarEntity
import com.example.thecars.data.MainDb
import com.example.thecars.data.NotesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarDetailsViewModel(val database: MainDb, val selectedCar: Car) : ViewModel() {

    private val _currentImageList = MutableLiveData(
        listOf(selectedCar.frontPhoto,
            selectedCar.backPhoto,
            selectedCar.sidePhoto))
    val currentImageList: LiveData<List<Int>>
        get() = _currentImageList

    val isCarExists = database.dao.doesCarExist(selectedCar.name)

    val existingNote = database.dao.getNoteByName(selectedCar.name)

    fun setOrUpdateNote(text: String) {
        if(text.isNotEmpty()) {
            val newNote = NotesEntity(text, selectedCar.name)
            viewModelScope.launch(Dispatchers.IO) {
                database.dao.insertNoteItem(newNote)
            }
            } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        database.dao.deleteNoteItem(existingNote.value!!)
                    }


        }
    }

    private fun setCarEntityFromCar(car: Car): CarEntity {
        return CarEntity(
            car.brand,
            car.model,
            car.name,
            car.previewPhoto
        )
    }

    fun addItemToDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
                database.dao.insertItem(setCarEntityFromCar(selectedCar))
        }
    }


    companion object {
        class CarDetailsViewModelFactory(private val app: App, private val car: Car) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CarDetailsViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return CarDetailsViewModel(app.database, car) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}