package com.example.thecars.domain.usecases

import com.example.thecars.data.Note

interface RemoveNoteFromDatabaseUseCase {

    suspend fun removeNote(note: Note)

}