package com.example.notesapp.ui.notesHomeScreen.view.notesListItems

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NoteItemNoteTextView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NoteItemTitleTextView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NotesListItemCardView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.VerticalGalleryLazyRow
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.mockData.mockNoteInfo
import com.example.notesapp.ui.theme.*

@Composable
fun NotesListItemTwo(
    noteInfo: NoteInfo,
    onEvent: (BaseComposeEvent) -> Unit
) {

    NotesListItemCardView(onClick = {}) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(spacing16)
        ) {
            Row() {
                NoteItemTitleTextView(title = noteInfo.title)
                
                Spacer(modifier = Modifier.padding(start = spacing8))
                
                if (noteInfo.noteImages?.isNotEmpty() == true) {
                    VerticalGalleryLazyRow(images = noteInfo.noteImages)
                }
            }

            Row(modifier = Modifier.padding(top = spacing8)) {
                NoteItemNoteTextView(
                    title = noteInfo.title,
                    note = noteInfo.note,
                    onEvent
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListItemTwoPreview() {
    NotesAppTheme {
        NotesListItemTwo(
            noteInfo = mockNoteInfo()[2],
            onEvent = {}
        )
    }
}