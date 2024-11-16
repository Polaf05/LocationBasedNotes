package com.reference.locationbasednotes.data.model

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val creationDate: String,
    val latitude: Double,
    val longitude: Double
)