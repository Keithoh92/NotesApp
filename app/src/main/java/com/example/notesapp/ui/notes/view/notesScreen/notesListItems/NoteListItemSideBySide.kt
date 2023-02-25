package com.example.notesapp.ui.notes.view.notesScreen.notesListItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.ui.notes.data.NoteInfo
import com.example.notesapp.ui.notes.data.mockNoteInfo
import com.example.notesapp.ui.notes.view.notesScreen.notesListItemComponents.NoteItemNoteTextView
import com.example.notesapp.ui.notes.view.notesScreen.notesListItemComponents.NoteItemTitleTextView
import com.example.notesapp.ui.notes.view.notesScreen.notesListItemComponents.GridViewGalleryTwoByTwo
import com.example.notesapp.ui.notes.view.notesScreen.notesListItemComponents.NotesListItemCardView
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.full
import com.example.notesapp.ui.theme.spacing16

@Composable
fun NotesListItemSideBySide(
    noteInfo: NoteInfo,
    onClick: () -> Unit,
    onEvent: (BaseComposeEvent) -> Unit
) {

    NotesListItemCardView(onClick = onClick) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(spacing16)
        ) {

            Column(
                modifier = Modifier
                    .weight(full)
            ) {
                NoteItemTitleTextView(title = noteInfo.title)

                NoteItemNoteTextView(
                    title = noteInfo.title,
                    note = noteInfo.note,
                    onEvent = onEvent
                )
            }

            Column(
                modifier = Modifier
                    .weight(full)
            ) {
                GridViewGalleryTwoByTwo(
                    title = noteInfo.title,
                    images = noteInfo.noteImages ?: emptyList(),
                    onEvent = onEvent
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
            onClick = {},
            onEvent = {}
        )
    }
}