package com.example.notesapp.feature.note

import java.util.Date

data class NoteInfo(
    val id: Int,
    val title: String,
    val note: String,
    val category: String?,
    val noteImages: List<String>?,
    val voiceNotes: List<String>?,
    val videoNotes: List<String>?,
    val date: Date,
    val timestamp: Date,
    val cardViewSelectedId: Int
)