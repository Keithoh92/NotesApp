package com.example.notesapp.ui.notes.view.notesScreen.notesListItems

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.notesapp.ui.notes.data.NoteInfo
import com.example.notesapp.ui.notes.data.mockNoteInfo
import com.example.notesapp.ui.notes.view.notesScreen.notesListItemComponents.NoteItemNoteTextView
import com.example.notesapp.ui.notes.view.notesScreen.notesListItemComponents.NoteItemTitleTextView
import com.example.notesapp.ui.notes.view.notesScreen.notesListItemComponents.NotesListItemCardView
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.event.NotesAppEvent
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.full
import com.example.notesapp.ui.theme.spacing16

@Composable
fun NotesListItemOne(
    noteInfo: NoteInfo,
    isMinimised: Map<Int, Boolean>,
    onEvent: (BaseComposeEvent) -> Unit,
    onClick: () -> Unit
) {

    val minimised = isMinimised[noteInfo.id]
    
    NotesListItemCardView(onClick = onClick) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(spacing16)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
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

                IconButton(onClick = {
                    onEvent(NotesAppEvent.OnMinimiseNote(noteInfo.id, minimised))
                }) {
                    if (minimised == true) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null

                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropUp,
                            contentDescription = null
                        )
                    }
                }
            }

            AnimatedVisibility (minimised != true) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    NoteItemNoteTextView(
                        title = noteInfo.title,
                        note = noteInfo.note,
                        onEvent = onEvent
                    )
                }
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
            isMinimised = emptyMap(),
            onEvent = {},
            onClick = {},
        )
    }
}