package com.example.notesapp.ui.notesHomeScreen.view.notesListItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.mockData.mockNoteInfo
import com.example.notesapp.ui.theme.*

// The row view for each note will be controlled by the settings that the user chooses

@Composable
fun NoteListItem(noteListing: NoteInfo) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            elevation = spacing10,
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing2)
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        start = spacing2,
                        top = spacing10,
                        bottom = spacing10),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = noteListing.id.toString(),
                    modifier = Modifier.weight(full),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListItemPreview() {
    NotesAppTheme {
        NoteListItem(noteListing = mockNoteInfo().first())
    }
}