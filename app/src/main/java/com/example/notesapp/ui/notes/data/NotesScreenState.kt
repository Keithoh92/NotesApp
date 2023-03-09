package com.example.notesapp.ui.notes.data

import com.example.notesapp.feature.note.NoteInfo

data class NotesScreenState(
    val notes: List<NoteInfo> = emptyList()
)