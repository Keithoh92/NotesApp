package com.example.notesapp.feature.notes.view.notesScreen.notesListItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.feature.notes.data.NoteInfo
import com.example.notesapp.feature.notes.data.mockNoteInfo
import com.example.notesapp.feature.notes.view.general.NoteItemNoteTextView
import com.example.notesapp.feature.notes.view.general.NoteItemTitleTextView
import com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents.GridViewGalleryTwoByTwo
import com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents.NotesListItemCardView
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.full
import com.example.notesapp.ui.theme.spacing8

@Composable
fun NotesListItemSideBySide(
    noteInfo: NoteInfo,
    onClick: () -> Unit
) {

    NotesListItemCardView(onClick = onClick) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(spacing8)
        ) {

            Column(
                modifier = Modifier
                    .padding(spacing8)
                    .weight(full)
            ) {
                NoteItemTitleTextView(title = noteInfo.title)

                NoteItemNoteTextView(note = noteInfo.note)
            }

            Column(
                modifier = Modifier
                    .padding(spacing8)
                    .weight(full)
            ) {
                GridViewGalleryTwoByTwo(
                    images = noteInfo.noteImages ?: emptyList(),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListItemSideBySidePreview() {
    NotesAppTheme {
        NotesListItemSideBySide(
            noteInfo = mockNoteInfo()[3],
            onClick = {}
        )
    }
}