package com.reference.locationbasednotes.data.repositories.note

import com.reference.locationbasednotes.data.model.Note
import javax.inject.Inject


class NoteRemoteDataSource @Inject constructor(
//    private val apiService: NoteApiService // Assuming you're using Retrofit
) {
    suspend fun fetchNotes(): List<Note> {
//        return apiService.getNotes() // Replace with your actual API call
    }
}