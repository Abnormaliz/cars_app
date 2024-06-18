package com.example.thecars.app.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecars.app.presentation.models.CarUi
import com.example.thecars.data.CarEntity
import com.example.thecars.data.NotesEntity
import com.example.thecars.domain.usecaseImpl.AddCarToDatabaseUseCaseImpl
import com.example.thecars.domain.usecaseImpl.AddNoteToDatabaseUseCaseImpl
import com.example.thecars.domain.usecaseImpl.CheckCarUseCaseImpl
import com.example.thecars.domain.usecaseImpl.GetNoteByNameUseCaseImpl
import com.example.thecars.domain.usecaseImpl.RemoveCarFromDatabaseUseCaseImpl
import com.example.thecars.domain.usecaseImpl.RemoveNoteFromDatabaseUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CarDetailsViewModel(
    private val selectedCar: CarUi,
    private val removeCarFromDatabaseUseCase: com.example.thecars.domain.usecaseImpl.RemoveCarFromDatabaseUseCaseImpl,
    private val addCarToDatabaseUseCase: com.example.thecars.domain.usecaseImpl.AddCarToDatabaseUseCaseImpl,
    private val addNoteToDatabaseUseCase: com.example.thecars.domain.usecaseImpl.AddNoteToDatabaseUseCaseImpl,
    private val removeNoteFromDatabaseUseCase: com.example.thecars.domain.usecaseImpl.RemoveNoteFromDatabaseUseCaseImpl,
    checkCarUseCase: com.example.thecars.domain.usecaseImpl.CheckCarUseCaseImpl,
    getNoteByNameUseCase: com.example.thecars.domain.usecaseImpl.GetNoteByNameUseCaseImpl
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

    val existingNote: StateFlow<com.example.thecars.data.NotesEntity?> = getNoteByNameUseCase.getNote(selectedCar.name).stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null)


    fun setOrUpdateNote(text: String) {
        if (text.isNotEmpty()) {
            val newNote = com.example.thecars.data.NotesEntity(text, selectedCar.name)
            viewModelScope.launch(Dispatchers.IO) {
                addNoteToDatabaseUseCase.addNote(newNote)
            }
        } else if (existingNote.value != null) {
            viewModelScope.launch(Dispatchers.IO) {
                removeNoteFromDatabaseUseCase.removeNote(existingNote.value!!)
            }
        }
    }

    private fun setCarEntityFromCar(car: CarUi): com.example.thecars.data.CarEntity {
        return com.example.thecars.data.CarEntity(
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
