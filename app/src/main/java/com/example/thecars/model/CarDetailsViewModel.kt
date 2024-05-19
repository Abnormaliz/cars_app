package com.example.thecars.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecars.classes.Car
import com.example.thecars.data.CarEntity
import com.example.thecars.data.MainDb
import com.example.thecars.data.NotesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarDetailsViewModel(val database: MainDb, val selectedCar: Car) : ViewModel() {

    private val _currentImageList = MutableStateFlow<List<Int>>(
        listOf(
            selectedCar.frontPhoto,
            selectedCar.backPhoto,
            selectedCar.sidePhoto
        )
    )
    val currentImageList: StateFlow<List<Int>>
        get() = _currentImageList

    val isCarExists = database.dao.doesCarExist(selectedCar.name)

    val existingNote = database.dao.getNoteByName(selectedCar.name)

    fun setOrUpdateNote(text: String) {
        if (text.isNotEmpty()) {
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

    fun removeItemFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            database.dao.deleteOneItem(setCarEntityFromCar(selectedCar))
        }
    }
}
