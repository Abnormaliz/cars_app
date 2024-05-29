package com.example.thecars.domain.usecases

import com.example.thecars.data.NotesEntity
import com.example.thecars.repositories.CarsRepository

class AddNoteToDatabaseUseCase(private val repository: CarsRepository) {

    suspend fun addNote(note: NotesEntity) {
        repository.addNoteToDatabase(note)
    }
}