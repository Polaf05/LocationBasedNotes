package com.reference.locationbasednotes.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.reference.locationbasednotes.data.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToNoteDetails: (Int) -> Unit,
    navigateToAddEditNote: () -> Unit,
    navigateToMap: () -> Unit
) {
    val notes by viewModel.notes.collectAsState()
    val filterOptions = listOf("Proximity", "Creation Date", "Location")
    val selectedFilter by viewModel.selectedFilter.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                actions = {
                    IconButton(onClick = { navigateToAddEditNote() }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Note")
                    }
                    IconButton(onClick = { navigateToMap() }) {
                        Icon(Icons.Default.LocationOn, contentDescription = "Map View")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToAddEditNote() }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Filter Options
            FilterSection(
                filterOptions = filterOptions,
                selectedFilter = selectedFilter,
                onFilterSelected = { viewModel.onFilterSelected(it) }
            )

            // Notes List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(notes.size) { note ->
                    NoteItem(
                        note = notes[note],
                        onClick = { navigateToNoteDetails(notes[note].id) }
                    )
                }
            }
        }
    }
}

@Composable
fun FilterSection(
    filterOptions: List<String>,
    selectedFilter: String,
    onFilterSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        filterOptions.forEach { filter ->
            Button(
                onClick = { onFilterSelected(filter) },
                colors = if (filter == selectedFilter) {
                    ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                } else {
                    ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                }
            ) {
                Text(filter)
            }
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
//        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Created on: ${note.creationDate}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}