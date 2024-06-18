package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.NotesEntity
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecases.RemoveNoteFromDatabaseUseCase

class RemoveNoteFromDatabaseUseCaseImpl(private val repository: CarsRepositoryImpl) : RemoveNoteFromDatabaseUseCase {

    override suspend fun removeNote(note: NotesEntity) {
        repository.removeNoteFromDatabase(note)
    }
}