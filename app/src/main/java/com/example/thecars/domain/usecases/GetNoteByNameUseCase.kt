package com.example.thecars.domain.usecases

import com.example.thecars.data.NotesEntity
import com.example.thecars.data.repository.CarsRepositoryImpl
import kotlinx.coroutines.flow.Flow

class GetNoteByNameUseCase(private val repository: CarsRepositoryImpl) {
    fun getNote(carName: String): Flow<NotesEntity?> = repository.getNoteByName(carName)
}