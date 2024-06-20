package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.Note
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecases.AddNoteToDatabaseUseCase

class AddNoteToDatabaseUseCaseImpl(private val repository: CarsRepositoryImpl) : AddNoteToDatabaseUseCase {

    override suspend fun addNote(note: Note) {
        repository.addNoteToDatabase(note)
    }
}