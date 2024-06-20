package com.example.thecars.domain.usecaseImpl

import com.example.thecars.data.Note
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecases.GetNoteByNameUseCase
import kotlinx.coroutines.flow.Flow

class GetNoteByNameUseCaseImpl(private val repository: CarsRepositoryImpl) : GetNoteByNameUseCase {
    override fun getNote(carName: String): Flow<Note?> = repository.getNoteByName(carName)
}