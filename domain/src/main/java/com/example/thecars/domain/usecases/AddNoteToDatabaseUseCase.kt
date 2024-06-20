package com.example.thecars.domain.usecases

import com.example.thecars.data.Note

interface AddNoteToDatabaseUseCase {

    suspend fun addNote(note: Note)

}