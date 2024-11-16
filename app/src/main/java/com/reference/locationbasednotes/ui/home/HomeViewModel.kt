package com.reference.locationbasednotes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reference.locationbasednotes.data.model.Note
import com.reference.locationbasednotes.data.repositories.note.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    private val _selectedFilter = MutableStateFlow("Proximity")
    val selectedFilter: StateFlow<String> = _selectedFilter

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            _notes.value = noteRepository.getNotes()
        }
    }

    fun onFilterSelected(filter: String) {
        _selectedFilter.value = filter
        viewModelScope.launch {
            _notes.value = when (filter) {
//                "Proximity" -> noteRepository.getNotesSortedByProximity()
//                "Creation Date" -> noteRepository.getNotesSortedByDate()
//                "Location" -> noteRepository.getNotesSortedByLocation()
                else -> _notes.value
            }
        }
    }
}
