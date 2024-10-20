package com.example.notesapp.ui.notesHomeScreen.state

import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.noteScreen.NoteScreenDialogDetails

data class NotesHomeScreenState(
    val notes: List<NoteInfo> = emptyList(),
    val noteClicked: Pair<Int, Boolean> = -1 to false,
    val revealedCardIds: Map<Int, Boolean> = mutableMapOf<Int, Boolean>().apply { put(-1, false) },
    val deleteNoteDialog: Boolean = false,
    val noteScreenDialogDetails: NoteScreenDialogDetails = NoteScreenDialogDetails(),
    val isLoading: Boolean = false,
    val shouldLoadPlaceHolderImages: Boolean = false
)