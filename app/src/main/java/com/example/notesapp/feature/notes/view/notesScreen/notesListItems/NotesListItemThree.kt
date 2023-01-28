package com.example.notesapp.feature.notes.view.notesScreen.notesListItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.feature.notes.data.NoteInfo
import com.example.notesapp.feature.notes.data.mockNoteInfo
import com.example.notesapp.feature.notes.view.general.NoteItemTitleTextView
import com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents.NotesListItemCardView
import com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents.VerticalGalleryLazyRow
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.spacing10
import com.example.notesapp.ui.theme.spacing8

@Composable
fun NotesListItemThree(
    noteInfo: NoteInfo,
    onClick: () -> Unit
) {

    NotesListItemCardView(onClick) {
         Column(
             verticalArrangement = Arrangement.spacedBy(spacing10),
             horizontalAlignment = Alignment.Start,
             modifier = Modifier
                 .padding(spacing8)
                 .fillMaxWidth()
         ) {
             NoteItemTitleTextView(title = noteInfo.title)

             if (noteInfo.noteImages?.isNotEmpty() == true) {
                 VerticalGalleryLazyRow(images = noteInfo.noteImages)
             }
         }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListItemThreePreview() {
    NotesAppTheme {
        NotesListItemThree(
            noteInfo = mockNoteInfo()[3],
            onClick = {}
        )
    }
}