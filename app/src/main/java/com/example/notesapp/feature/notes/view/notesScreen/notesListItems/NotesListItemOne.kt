package com.example.notesapp.feature.notes.view.notesScreen.notesListItems

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.notesapp.feature.notes.data.NoteInfo
import com.example.notesapp.feature.notes.data.mockNoteInfo
import com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents.NoteItemNoteTextView
import com.example.notesapp.feature.notes.view.general.NoteItemTitleTextView
import com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents.NotesListItemCardView
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.full
import com.example.notesapp.ui.theme.spacing16

@Composable
fun NotesListItemOne(
    noteInfo: NoteInfo,
    onClick: () -> Unit,
    onLongPressNote: (String, String) -> Unit,
    onReleaseLongPressNote: () -> Unit
) {
    
    NotesListItemCardView(onClick = onClick) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(spacing16)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NoteItemTitleTextView(title = noteInfo.title)

                Spacer(modifier = Modifier.weight(full))

                if (noteInfo.category?.isNotEmpty() == true) {
                    Text(
                        text = noteInfo.category.toString(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
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
fun NotesListItemOnePreview() {
    NotesAppTheme {
        NotesListItemOne(
            noteInfo = mockNoteInfo().first(),
            onClick = {},
            {_, _ ->},
            {}
        )
    }
}