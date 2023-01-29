package com.example.notesapp.feature.notes.view.notesScreen.notesListItems

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.feature.notes.data.NoteInfo
import com.example.notesapp.feature.notes.data.mockNoteInfo
import com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents.NoteItemNoteTextView
import com.example.notesapp.feature.notes.view.general.NoteItemTitleTextView
import com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents.NotesListItemCardView
import com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents.VerticalGalleryLazyRow
import com.example.notesapp.ui.theme.*

@Composable
fun NotesListItemTwo(
    noteInfo: NoteInfo,
    onLongPressNote: (String, String) -> Unit,
    onReleaseLongPressNote: () -> Unit
) {

    NotesListItemCardView(onClick = {}) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(spacing16)
        ) {
            Row {
                NoteItemTitleTextView(title = noteInfo.title)
                
                Spacer(modifier = Modifier.weight(full))
                
                if (noteInfo.noteImages?.isNotEmpty() == true) {
                    VerticalGalleryLazyRow(images = noteInfo.noteImages)
                }
            }

            Row(modifier = Modifier.padding(top = spacing8)) {
                NoteItemNoteTextView(
                    title = noteInfo.title,
                    note = noteInfo.note,
                    onLongPressNote,
                    onReleaseLongPressNote
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
            { _, _ ->},
            {}
        )
    }
}