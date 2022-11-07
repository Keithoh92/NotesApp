package com.example.notesapp.feature.homescreen.data

import java.util.Date

data class NoteInfo(
    val id: Int,
    val title: String,
    val note: String,
    val noteImages: List<String>?,
    val voiceNotes: List<String>?,
    val videoNotes: List<String>?,
    val date: Date,
    val timestamp: Date
)
