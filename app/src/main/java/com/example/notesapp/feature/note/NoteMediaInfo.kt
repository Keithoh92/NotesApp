package com.example.notesapp.feature.note

import com.example.notesapp.domain.enum.MediaType

data class NoteMediaInfo(
    val id: Int,
    val path: String,
    val mediaType: MediaType
)
