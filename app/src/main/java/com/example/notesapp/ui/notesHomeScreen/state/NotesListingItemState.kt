package com.example.notesapp.ui.notesHomeScreen.state

data class NotesListingItemState(
    val showNoteItemPreview: Boolean = false,
    val noteTitle: String = "Note 1",
    val imageToShow: String? = null,
    val noteToShow: String? = null,
    val notesMinimised: Map<Int, Boolean> = mutableMapOf<Int, Boolean>().apply { put(-1, false) }
)