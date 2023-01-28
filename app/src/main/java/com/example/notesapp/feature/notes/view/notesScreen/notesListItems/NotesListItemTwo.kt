package com.example.notesapp.feature.notes.view.notesScreen.notesListItems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.feature.notes.data.NoteInfo
import com.example.notesapp.feature.notes.data.mockNoteInfo
import com.example.notesapp.feature.notes.view.general.NoteItemNoteTextView
import com.example.notesapp.feature.notes.view.general.NoteItemTitleTextView
import com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents.VerticalGalleryLazyRow
import com.example.notesapp.ui.theme.*

@Composable
fun NotesListItemTwo(
    noteInfo: NoteInfo
) {

    Card(
        elevation = 10.dp,
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(spacing8)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(spacing8)
            ) {
                NoteItemTitleTextView(title = noteInfo.title)
                
                Spacer(modifier = Modifier.weight(full))
                
                if (noteInfo.noteImages?.isNotEmpty() == true) {
                    VerticalGalleryLazyRow(images = noteInfo.noteImages)
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                    .padding(spacing8)
            ) {
                NoteItemNoteTextView(note = noteInfo.note)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListItemTwoPreview() {
    NotesAppTheme {
        NotesListItemTwo(noteInfo = mockNoteInfo()[2])
    }
}