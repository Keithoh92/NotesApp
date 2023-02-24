package com.example.notesapp.ui.notes.view.notesScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.R
import com.example.notesapp.ui.notes.data.NotesListingItemState
import com.example.notesapp.ui.notes.data.mockNotesList
import com.example.notesapp.ui.notes.view.TopAppBarHomeScreen
import com.example.notesapp.ui.notes.view.notesScreen.notesListItemComponents.NoteItemPreviewer
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.theme.NotesAppTheme

@Composable
fun NotesScreen(
    notesListingItemState: NotesListingItemState,
    onEvent: (BaseComposeEvent) -> Unit,
    openDrawer: () -> Unit
) {
    
    NotesAppTheme {
        Scaffold(
            topBar = {
                TopAppBarHomeScreen(onButtonClicked = openDrawer)
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = it.calculateBottomPadding(),
                            top = it.calculateTopPadding()
                        )
                        .background(MaterialTheme.colors.primaryVariant)
                ) {
                    NotesList(
                        notesList = mockNotesList(),
                        notesListingItemState = notesListingItemState,
                        onEvent = onEvent
                    )

                    AnimatedVisibility(visible = notesListingItemState.showNoteItemPreview) {
                        NoteItemPreviewer(
                            title = notesListingItemState.noteTitle,
                            image = notesListingItemState.imageToShow,
                            note = notesListingItemState.noteToShow
                        )
                    }
                }            
            },
            bottomBar = {
                NotesScreenBottomAppBar(
                    onEvent = onEvent
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* Perform some action */ },
                    backgroundColor = colorResource(id = R.color.light_grey_app).copy(0.75f),
                    shape = CircleShape,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Filled.Add, "")
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = true,
            backgroundColor = MaterialTheme.colors.primaryVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NotesAppTheme {
        NotesScreen(
            openDrawer = {},
            notesListingItemState = NotesListingItemState(),
            onEvent = {}
        )
    }
}