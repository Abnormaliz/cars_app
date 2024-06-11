package com.example.thecars.domain.usecases

import com.example.thecars.data.NotesEntity

interface AddNoteToDatabaseUseCase {

    suspend fun addNote(note: NotesEntity)

}