package com.example.notesapp.ui.noteScreen.state

import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.mockData.mockNotesList
import com.example.notesapp.ui.noteScreen.NoteScreenDialogDetails

data class NoteScreenState(
    val isNewNote: Boolean = true,
    val existingNote: NoteInfo = mockNotesList().first(),
    val currentTitle: String = "",
    val currentNote: String = "",
    val saveChangesDialog: Boolean = false,
    val shouldReturnToHome: Boolean = false,
    val noteScreenDialogDetails: NoteScreenDialogDetails = NoteScreenDialogDetails()
)
