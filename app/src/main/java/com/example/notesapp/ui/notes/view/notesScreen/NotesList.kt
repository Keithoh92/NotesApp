package com.example.notesapp.ui.notes.view.notesScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.notes.data.*
import com.example.notesapp.ui.notes.view.notesScreen.notesListItems.NotesListItemOne
import com.example.notesapp.ui.notes.view.notesScreen.notesListItems.NotesListItemSideBySide
import com.example.notesapp.ui.notes.view.notesScreen.notesListItems.NotesListItemThree
import com.example.notesapp.ui.notes.view.notesScreen.notesListItems.NotesListItemTwo
import com.example.notesapp.ui.event.BaseComposeEvent
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
    notesListingItemState: NotesListingItemState,
    onEvent: (BaseComposeEvent) -> Unit
    ) {
    LazyColumn(
        modifier = Modifier
            .padding(spacing8)
    ) {
        items(notesList.size) {
            Column(modifier = Modifier.fillMaxSize().padding(bottom = spacing8)) {
                val noteCardViewId = notesList[it]
                when (noteCardViewId.cardViewSelectedId) {
                    1 -> NotesListItemOne(noteInfo = notesList[it], isMinimised = notesListingItemState.notesMinimised, onEvent, onClick = {})
                    2 -> NotesListItemTwo(noteInfo = notesList[it], onEvent)
                    3 -> {
                        // TODO - Will be fixed cleaned and refactored in future iterations
                        if (notesList[it].note.isNotEmpty()) NotesListItemOne(noteInfo = notesList[it], isMinimised = notesListingItemState.notesMinimised, onEvent, onClick = {})
                        else NotesListItemThree(noteInfo = notesList[it], onClick = {})
                    }
                    4 -> {
                        if (notesList[it].noteImages?.isNotEmpty() == true) {
                            NotesListItemSideBySide(
                                noteInfo = notesList[it],
                                onClick = {},
                                onEvent = onEvent
                            )
                        } else {
                            NotesListItemOne(noteInfo = notesList[it], isMinimised = notesListingItemState.notesMinimised, onEvent, onClick = {})
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
            notesListingItemState = mockNotesListState(),
            onEvent = {}
        )
    }
}