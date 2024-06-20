package com.example.thecars.domain.usecases

import com.example.thecars.data.Note
import kotlinx.coroutines.flow.Flow

interface GetNoteByNameUseCase {

    fun getNote(carName: String): Flow<Note?>

}