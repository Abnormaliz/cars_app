package com.example.thecars.domain.usecases

import com.example.thecars.data.NotesEntity
import com.example.thecars.data.repository.CarsRepositoryImpl

class RemoveNoteFromDatabaseUseCase(private val repository: CarsRepositoryImpl) {

    suspend fun removeNote(note: NotesEntity) {
        repository.removeNoteFromDatabase(note)
    }
}