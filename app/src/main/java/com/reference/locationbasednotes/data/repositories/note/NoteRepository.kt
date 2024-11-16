package com.reference.locationbasednotes.data.repositories.note

import com.reference.locationbasednotes.data.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteRemoteDatasource: NoteRemoteDataSource,
    private val noteLocalDatasource: NoteLocalDataSource
) {
    suspend fun getNotes(): List<Note> {
        return noteLocalDatasource.getNotes()
    }

    suspend fun getNotesSortedByProximity(): List<Note> {
        // Example logic to sort notes by proximity (stubbed here)
        return noteLocalDatasource.getNotes().sortedBy { it.latitude + it.longitude }
    }

    suspend fun getNotesSortedByDate(): List<Note> {
        return noteLocalDatasource.getNotes().sortedByDescending { it.creationDate }
    }

    suspend fun getNotesSortedByLocation(): List<Note> {
        return noteLocalDatasource.getNotes().sortedBy { it.latitude }
    }

    suspend fun syncNotesWithServer() {
        val notes = remoteDatasource.fetchNotes()
        localDatasource.saveNotes(notes)
    }

    suspend fun getNoteById(noteId: Int): Note? {
        return noteLocalDatasource.getNoteById(noteId)
    }
}