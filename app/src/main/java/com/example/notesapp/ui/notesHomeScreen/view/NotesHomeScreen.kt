package com.example.notesapp.ui.notesHomeScreen.view

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.R
import com.example.notesapp.ui.notesHomeScreen.state.NotesListingItemState
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NoteItemPreviewer
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.notesHomeScreen.state.NotesHomeScreenState
import com.example.notesapp.ui.theme.NotesAppTheme

@Composable
fun NotesHomeScreen(
    notesListingItemState: NotesListingItemState,
    notesHomeScreenState: NotesHomeScreenState,
    onEvent: (BaseComposeEvent) -> Unit,
    notesHomeScreenBottomAppBar: @Composable () -> Unit,
    navController: NavController
) {
    Scaffold(
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
                    notesList = notesHomeScreenState.notes,
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
        bottomBar = { notesHomeScreenBottomAppBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("Note") },
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

@Preview(showBackground = true)
@Composable
fun NotesHomeScreenPreview() {
    val navController = rememberNavController()
    NotesAppTheme {
        NotesHomeScreen(
            notesListingItemState = NotesListingItemState(),
            notesHomeScreenState = NotesHomeScreenState(),
            notesHomeScreenBottomAppBar = { NotesHomeScreenBottomAppBar(onEvent = {}) },
            onEvent = {},
            navController = navController
        )
    }
}