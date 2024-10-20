package com.example.notesapp.ui.notesHomeScreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.notesHomeScreen.view.notesListItems.NotesListItemOne
import com.example.notesapp.ui.notesHomeScreen.view.notesListItems.NotesListItemSideBySide
import com.example.notesapp.ui.notesHomeScreen.view.notesListItems.NotesListItemThree
import com.example.notesapp.ui.notesHomeScreen.view.notesListItems.NotesListItemTwo
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.mockData.mockNotesList
import com.example.notesapp.ui.mockData.mockNotesListState
import com.example.notesapp.ui.notesHomeScreen.state.NotesHomeScreenState
import com.example.notesapp.ui.notesHomeScreen.state.NotesListingItemState
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.spacing8


/*
*   Notes card views will need to be draggable and each note can have any card view it wants to use
*   If a card view is minimised to half the screen, then the next one beside will need to be the same or empty
*
* */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotesList(
    notesList: List<NoteInfo>,
    notesListingItemState: NotesListingItemState,
    notesHomeScreenState: NotesHomeScreenState,
    onEvent: (BaseComposeEvent) -> Unit,
    navController: NavController
    ) {
    LazyColumn(
        modifier = Modifier
            .padding(spacing8)
    ) {
        items(
            count = notesList.size,
            key = { notesList[it].id }
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = spacing8)) {
                val noteCardViewId = notesList[it]

                when (noteCardViewId.cardViewSelectedId) {
                    1 -> NotesListItemOne(noteInfo = notesList[it], isMinimised = notesListingItemState.notesMinimised, notesHomeScreenState.revealedCardIds, onEvent, navController = navController)
                    2 -> NotesListItemTwo(noteInfo = notesList[it], notesHomeScreenState.revealedCardIds, onEvent, navController, notesHomeScreenState.shouldLoadPlaceHolderImages)
                    3 -> {
                        // TODO - Will be fixed cleaned and refactored in future iterations
                        if (notesList[it].note.isNotEmpty()) NotesListItemOne(noteInfo = notesList[it], isMinimised = notesListingItemState.notesMinimised, notesHomeScreenState.revealedCardIds, onEvent, navController = navController)
                        else NotesListItemThree(
                            noteInfo = notesList[it],
                            {},
                            notesHomeScreenState.revealedCardIds, onEvent,
                            navController = navController
                        )
                    }
                    4 -> {
                        if (notesList[it].noteImages?.isNotEmpty() == true) {
                            NotesListItemSideBySide(
                                noteInfo = notesList[it],
                                onClick = {},
                                notesHomeScreenState.revealedCardIds, onEvent
                            )
                        } else {
                            NotesListItemOne(noteInfo = notesList[it], isMinimised = notesListingItemState.notesMinimised, notesHomeScreenState.revealedCardIds, onEvent, navController = navController)
                        }
                    }
                    5 -> NotesListItemThree(
                        noteInfo = notesList[it],
                        {},
                        notesHomeScreenState.revealedCardIds, onEvent,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListPreview() {
    val navController = rememberNavController()
    NotesAppTheme {
        NotesList(
            notesList = mockNotesList(),
            notesListingItemState = mockNotesListState(),
            notesHomeScreenState = NotesHomeScreenState(),
            onEvent = {},
            navController = navController
        )
    }
}