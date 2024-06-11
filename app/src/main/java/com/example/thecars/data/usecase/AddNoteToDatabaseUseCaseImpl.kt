package com.example.thecars.data.usecase

import com.example.thecars.data.NotesEntity
import com.example.thecars.data.repository.CarsRepositoryImpl
import com.example.thecars.domain.usecases.AddNoteToDatabaseUseCase

class AddNoteToDatabaseUseCaseImpl(private val repository: CarsRepositoryImpl) :
    AddNoteToDatabaseUseCase {

    override suspend fun addNote(note: NotesEntity) {
        repository.addNoteToDatabase(note)
    }
}