package com.example.notesapp.feature.notes.data

data class NotesListingItemState(
    val showNoteItemPreview: Boolean = false,
    val imageToShow: Int? = null,
    val noteToShow: String? = null
)