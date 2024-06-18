package com.example.thecars.domain.usecases

import com.example.thecars.data.NotesEntity

interface RemoveNoteFromDatabaseUseCase {

    suspend fun removeNote(note: NotesEntity)

}