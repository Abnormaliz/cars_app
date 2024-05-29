package com.example.thecars.domain.usecases

import com.example.thecars.data.NotesEntity
import com.example.thecars.repositories.CarsRepository

class RemoveNoteFromDatabaseUseCase(private val repository: CarsRepository) {

    suspend fun removeNote(note: NotesEntity) {
        repository.removeNoteFromDatabase(note)
    }
}