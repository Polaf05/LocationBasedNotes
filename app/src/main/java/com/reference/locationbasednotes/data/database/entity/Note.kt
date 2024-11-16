package com.reference.locationbasednotes.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val creationDate: String, // Use ISO 8601 format (e.g., "2024-11-16")
    val latitude: Double,
    val longitude: Double
)