package com.example.thecars.data.usecase

import com.example.thecars.data.NotesEntity
import com.example.thecars.data.repository.CarsRepositoryImpl
import com.example.thecars.domain.usecases.GetNoteByNameUseCase
import kotlinx.coroutines.flow.Flow

class GetNoteByNameUseCaseImpl(private val repository: CarsRepositoryImpl) : GetNoteByNameUseCase {
    override fun getNote(carName: String): Flow<NotesEntity?> = repository.getNoteByName(carName)
}