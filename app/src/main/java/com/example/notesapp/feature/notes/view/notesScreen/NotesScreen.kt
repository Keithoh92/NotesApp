package com.example.notesapp.feature.notes.view

import androidx.compose.animation.AnimatedVisibility
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
import com.example.notesapp.feature.notes.data.NotesListingItemState
import com.example.notesapp.feature.notes.data.mockNotesList
import com.example.notesapp.feature.notes.view.notesScreen.NotesList
import com.example.notesapp.feature.notes.view.notesScreen.NotesScreenBottomAppBar
import com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents.NoteItemPreviewer
import com.example.notesapp.ui.theme.NotesAppTheme

@Composable
fun NotesScreen(
    onClickSearch: () -> Unit,
    onClickShare: () -> Unit,
    onClickNotifications: () -> Unit,
    openDrawer: () -> Unit,
    notesListingItemState: NotesListingItemState,
    onLongPressImage: (Int) -> Unit,
    onReleaseLongPressImage: () -> Unit,
    onLongPressNote: (String) -> Unit,
    onReleaseLongPressNote: () -> Unit
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
                ) {
                    NotesList(
                        notesList = mockNotesList(),
                        onNoteClicked = {},
                        onLongPressImage,
                        onReleaseLongPressImage,
                        onLongPressNote,
                        onReleaseLongPressNote
                    )

                    AnimatedVisibility(visible = notesListingItemState.showNoteItemPreview) {
                        NoteItemPreviewer(
                            image = notesListingItemState.imageToShow,
                            note = notesListingItemState.noteToShow
                        )
                    }
                }            
            },
            bottomBar = {
                NotesScreenBottomAppBar(
                    onClickSearch = onClickSearch,
                    onClickShare = onClickShare,
                    onClickNotifications = onClickNotifications
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
            isFloatingActionButtonDocked = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NotesAppTheme {
        NotesScreen(
            onClickSearch = {},
            onClickShare = {},
            onClickNotifications = {},
            openDrawer = {},
            notesListingItemState = NotesListingItemState(),
            onLongPressImage = {},
            onReleaseLongPressImage = {},
            onLongPressNote = {},
            onReleaseLongPressNote = {}
        )
    }
}