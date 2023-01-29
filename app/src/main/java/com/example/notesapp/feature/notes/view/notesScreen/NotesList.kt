package com.example.notesapp.feature.notes.view.notesScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.feature.notes.data.NoteInfo
import com.example.notesapp.feature.notes.data.mockNotesList
import com.example.notesapp.feature.notes.view.notesScreen.notesListItems.NotesListItemOne
import com.example.notesapp.feature.notes.view.notesScreen.notesListItems.NotesListItemSideBySide
import com.example.notesapp.feature.notes.view.notesScreen.notesListItems.NotesListItemThree
import com.example.notesapp.feature.notes.view.notesScreen.notesListItems.NotesListItemTwo
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.spacing8


/*
*   Notes card views will need to be draggable and each note can have any card view it wants to use
*   If a card view is minimised to half the screen, then the next one beside will need to be the same or empty
*
* */
@Composable
fun NotesList(
    notesList: List<NoteInfo>,
    onNoteClicked: () -> Unit,
    onLongPressImage: (String, Int) -> Unit,
    onReleaseLongPress: () -> Unit,
    onLongPressNote: (String, String) -> Unit,
    onReleaseLongPressNote: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(spacing8)
    ) {
        items(notesList.size) {
            Column(modifier = Modifier.fillMaxSize()) {
                val noteCardViewId = notesList[it]
                when (noteCardViewId.cardViewSelectedId) {
                    1 -> NotesListItemOne(noteInfo = notesList[it], onClick = onNoteClicked, onLongPressNote, onReleaseLongPressNote)
                    2 -> NotesListItemTwo(noteInfo = notesList[it], onLongPressNote, onReleaseLongPressNote)
                    3 -> {
                        // TODO - Will be fixed cleaned and refactored in future iterations
                        if (notesList[it].note.isNotEmpty()) NotesListItemOne(noteInfo = notesList[it], onClick = onNoteClicked, onLongPressNote, onReleaseLongPressNote)
                        else NotesListItemThree(noteInfo = notesList[it], onClick = onNoteClicked)
                    }
                    4 -> {
                        if (notesList[it].noteImages?.isNotEmpty() == true) {
                            NotesListItemSideBySide(
                                noteInfo = notesList[it],
                                onClick = onNoteClicked,
                                onLongPressImage,
                                onReleaseLongPress,
                                onLongPressNote,
                                onReleaseLongPressNote
                            )
                        } else {
                            NotesListItemOne(noteInfo = notesList[it], onClick = onNoteClicked, onLongPressNote, onReleaseLongPressNote)
                        }
                    }
                    5 -> NotesListItemThree(noteInfo = notesList[it], {})
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListPreview() {
    NotesAppTheme {
        NotesList(
            notesList = mockNotesList(),
            onNoteClicked = {},
            onLongPressImage = { _, _ ->},
            onReleaseLongPress = {},
            onLongPressNote = { _, _ -> },
            onReleaseLongPressNote = {}
        )
    }
}