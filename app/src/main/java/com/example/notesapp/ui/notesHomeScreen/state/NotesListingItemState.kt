package com.example.notesapp.ui.notesHomeScreen.state

import coil.compose.AsyncImagePainter

data class NotesListingItemState(
    val showNoteItemPreview: Boolean = false,
    val noteTitle: String = "Note 1",
    val imageToShow: AsyncImagePainter? = null,
    val noteToShow: String? = null,
    val notesMinimised: Map<Int, Boolean> = mutableMapOf<Int, Boolean>().apply { put(-1, false) }
)