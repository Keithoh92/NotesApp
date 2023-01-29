package com.example.notesapp.feature.notes.data

data class NotesListingItemState(
    val showNoteItemPreview: Boolean = false,
    val noteTitle: String = "Note 1",
    val imageToShow: Int? = null,
    val noteToShow: String? = null
)