package com.example.thecars.domain.usecases

import com.example.thecars.data.NotesEntity
import com.example.thecars.repositories.CarsRepository
import kotlinx.coroutines.flow.Flow

class GetNoteByNameUseCase(private val repository: CarsRepository) {
    fun getNote(carName: String): Flow<NotesEntity?> = repository.getNoteByName(carName)
}