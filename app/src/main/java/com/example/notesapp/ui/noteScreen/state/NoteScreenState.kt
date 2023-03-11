package com.example.notesapp.ui.noteScreen.state

import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.mockData.mockNotesList

data class NoteScreenState(
    val isNewNote: Boolean = true,
    val note: NoteInfo = mockNotesList().first()
)
