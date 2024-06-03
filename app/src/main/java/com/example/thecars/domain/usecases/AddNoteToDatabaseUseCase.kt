package com.example.thecars.domain.usecases

import com.example.thecars.data.NotesEntity
import com.example.thecars.data.repository.CarsRepositoryImpl

class AddNoteToDatabaseUseCase(private val repository: CarsRepositoryImpl) {

    suspend fun addNote(note: NotesEntity) {
        repository.addNoteToDatabase(note)
    }
}