package com.example.thecars.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecars.classes.Car
import com.example.thecars.data.CarEntity
import com.example.thecars.data.NotesEntity
import com.example.thecars.domain.usecases.AddCarToDatabaseUseCase
import com.example.thecars.domain.usecases.AddNoteToDatabaseUseCase
import com.example.thecars.domain.usecases.CheckCarUseCase
import com.example.thecars.domain.usecases.GetNoteByNameUseCase
import com.example.thecars.domain.usecases.RemoveCarFromDatabaseUseCase
import com.example.thecars.domain.usecases.RemoveNoteFromDatabaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CarDetailsViewModel(
    private val selectedCar: Car,
    private val removeCarFromDatabaseUseCase: RemoveCarFromDatabaseUseCase,
    private val addCarToDatabaseUseCase: AddCarToDatabaseUseCase,
    private val addNoteToDatabaseUseCase: AddNoteToDatabaseUseCase,
    private val removeNoteFromDatabaseUseCase: RemoveNoteFromDatabaseUseCase,
    checkCarUseCase: CheckCarUseCase,
    getNoteByNameUseCase: GetNoteByNameUseCase
) : ViewModel() {

    private val _currentImageList = MutableStateFlow(
        listOf(
            selectedCar.frontPhoto,
            selectedCar.backPhoto,
            selectedCar.sidePhoto
        )
    )
    val currentImageList: StateFlow<List<Int>>
        get() = _currentImageList

    val isCarExists = checkCarUseCase.checkCar(selectedCar.name)

    val existingNote: StateFlow<NotesEntity?> = getNoteByNameUseCase.getNote(selectedCar.name).stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null)


    fun setOrUpdateNote(text: String) {
        if (text.isNotEmpty()) {
            val newNote = NotesEntity(text, selectedCar.name)
            viewModelScope.launch(Dispatchers.IO) {
                addNoteToDatabaseUseCase.addNote(newNote)
            }
        } else if (existingNote.value != null) {
            viewModelScope.launch(Dispatchers.IO) {
                removeNoteFromDatabaseUseCase.removeNote(existingNote.value!!)
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

    fun addCar() {
        viewModelScope.launch(Dispatchers.IO) {
            addCarToDatabaseUseCase.addCar(setCarEntityFromCar(selectedCar))
        }
    }

    fun removeCar() {
        viewModelScope.launch(Dispatchers.IO) {
            removeCarFromDatabaseUseCase.removeCar(setCarEntityFromCar(selectedCar))
        }
    }
}
