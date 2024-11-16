package com.reference.locationbasednotes.data.repositories.note

import com.reference.locationbasednotes.data.database.dao.NoteDao
import com.reference.locationbasednotes.data.database.entity.Note
import javax.inject.Inject

class NoteLocalDataSource @Inject constructor(
    private val noteDao: NoteDao // Assuming you're using Room
) {
    suspend fun getNotes(): List<Note> {
        return noteDao.getAllNotes()
    }

    suspend fun saveNotes(notes: List<Note>) {
        noteDao.insertNotes(notes)
    }

    suspend fun getNoteById(noteId: Int): Note? {
        return noteDao.getNoteById(noteId)
    }
}