package com.example.notesapp.ui.noteScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.R
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.noteScreen.state.NoteScreenState
import com.example.notesapp.ui.TopAppBarHomeScreen
import com.example.notesapp.ui.mockData.mockNotesList
import com.example.notesapp.ui.noteScreen.event.NoteScreenEvent
import com.example.notesapp.ui.noteScreen.view.noteScreenComponents.NoteText
import com.example.notesapp.ui.noteScreen.view.noteScreenComponents.NoteTitle
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.spacing8

@Composable
fun NoteScreen(
    noteScreenState: NoteScreenState,
    onEvent: (BaseComposeEvent) -> Unit,
    noteScreenBottomAppBar: @Composable () -> Unit
) {
    Scaffold(
        content = {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = it.calculateBottomPadding(),
                        top = it.calculateTopPadding()
                    )
                    .background(colorResource(id = R.color.dark_grey_app))
            ) {
                Row(modifier = Modifier.padding(spacing8)) {
                    NoteTitle(
                        title = if (noteScreenState.isNewNote) "" else noteScreenState.note.title,
                        onEvent = onEvent
                    )
                }

                Divider(modifier = Modifier.height(1.dp), color = colorResource(id = R.color.light_grey_app))

                Column(modifier = Modifier.padding(spacing8)) {
                    NoteText(
                        note = if (noteScreenState.isNewNote) "" else noteScreenState.note.note,
                        onEvent = onEvent
                    )
                }
            }
        },
        bottomBar = {
            noteScreenBottomAppBar()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(
        noteScreenState = NoteScreenState(false, mockNotesList().first()),
        onEvent = {}) {
    }
}