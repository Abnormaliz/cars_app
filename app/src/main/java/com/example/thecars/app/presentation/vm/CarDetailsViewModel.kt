package com.example.thecars.app.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecars.app.presentation.models.CarUi
import com.example.thecars.app.presentation.models.toCar
import com.example.thecars.data.Note
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
    private val removeCarFromDatabaseUseCase: RemoveCarFromDatabaseUseCaseImpl,
    private val addCarToDatabaseUseCase: AddCarToDatabaseUseCaseImpl,
    private val addNoteToDatabaseUseCase: AddNoteToDatabaseUseCaseImpl,
    private val removeNoteFromDatabaseUseCase: RemoveNoteFromDatabaseUseCaseImpl,
    checkCarUseCase: CheckCarUseCaseImpl,
    getNoteByNameUseCase: GetNoteByNameUseCaseImpl
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

    val existingNote: StateFlow<Note?> = getNoteByNameUseCase.getNote(selectedCar.name).stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null
    )


    fun setOrUpdateNote(text: String) {
        if (text.isNotEmpty()) {
            val newNote = Note(text, selectedCar.name)
            viewModelScope.launch(Dispatchers.IO) {
                addNoteToDatabaseUseCase.addNote(newNote)
            }
        } else if (existingNote.value != null) {
            viewModelScope.launch(Dispatchers.IO) {
                removeNoteFromDatabaseUseCase.removeNote(existingNote.value!!)
            }
        }
    }

    fun addCar() {
        viewModelScope.launch(Dispatchers.IO) {
            addCarToDatabaseUseCase.addCar(selectedCar.toCar())
        }
    }

    fun removeCar() {
        viewModelScope.launch(Dispatchers.IO) {
            removeCarFromDatabaseUseCase.removeCar(selectedCar.toCar())
        }
    }
}
