package com.example.thecars.data.usecase

import com.example.thecars.data.NotesEntity
import com.example.thecars.data.repository.CarsRepositoryImpl
import com.example.thecars.domain.usecases.RemoveNoteFromDatabaseUseCase

class RemoveNoteFromDatabaseUseCaseImpl(private val repository: CarsRepositoryImpl) :
    RemoveNoteFromDatabaseUseCase {

    override suspend fun removeNote(note: NotesEntity) {
        repository.removeNoteFromDatabase(note)
    }
}