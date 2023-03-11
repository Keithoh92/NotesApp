package com.example.notesapp.ui.notesHomeScreen.state

import com.example.notesapp.feature.note.NoteInfo

data class NotesHomeScreenState(
    val notes: List<NoteInfo> = emptyList()
)