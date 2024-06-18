package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.NotesEntity
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecases.AddNoteToDatabaseUseCase

class AddNoteToDatabaseUseCaseImpl(private val repository: CarsRepositoryImpl) : AddNoteToDatabaseUseCase {

    override suspend fun addNote(note: NotesEntity) {
        repository.addNoteToDatabase(note)
    }
}