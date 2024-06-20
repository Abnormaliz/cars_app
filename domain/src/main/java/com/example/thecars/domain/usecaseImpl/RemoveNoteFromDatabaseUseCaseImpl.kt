package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.Note
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecases.RemoveNoteFromDatabaseUseCase

class RemoveNoteFromDatabaseUseCaseImpl(private val repository: CarsRepositoryImpl) : RemoveNoteFromDatabaseUseCase {

    override suspend fun removeNote(note: Note) {
        repository.removeNoteFromDatabase(note)
    }
}