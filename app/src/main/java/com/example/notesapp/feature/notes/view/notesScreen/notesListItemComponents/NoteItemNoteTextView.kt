package com.example.notesapp.feature.notes.view.general

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun NoteItemNoteTextView(
    note: String
) {
    Text(
        text = note,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis
    )
}