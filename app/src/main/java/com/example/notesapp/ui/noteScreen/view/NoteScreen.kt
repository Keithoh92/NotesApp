package com.example.notesapp.ui.noteScreen.view

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.R
import com.example.notesapp.common.logs.NLog
import com.example.notesapp.ui.common.NotesAlertDialog
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.mockData.mockNotesList
import com.example.notesapp.ui.noteScreen.event.NoteScreenEvent
import com.example.notesapp.ui.noteScreen.state.NoteScreenState
import com.example.notesapp.ui.noteScreen.view.noteScreenComponents.NoteText
import com.example.notesapp.ui.noteScreen.view.noteScreenComponents.NoteTitle
import com.example.notesapp.ui.theme.spacing16
import com.example.notesapp.ui.theme.spacing8
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NoteScreen(
    noteScreenState: StateFlow<NoteScreenState>,
    onEvent: (BaseComposeEvent) -> Unit,
    noteScreenBottomAppBar: @Composable () -> Unit,
    noteId: String? = null
) {

    val state by noteScreenState.collectAsState()

    Log.d(TAG, "NoteScreen: noteId = $noteId")
    BackHandler(true) {
        onEvent(NoteScreenEvent.OnBackClicked)
    }
    
    if (state.shouldReturnToHome) {
        navController.popBackStack("note", inclusive = true)
    }

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
                    NLog.d("title = ${noteScreenState.existingNote.title}")
                    NoteTitle(
                        title = if (noteScreenState.isNewNote) "" else noteScreenState.currentTitle,
                        isNewNote = noteScreenState.isNewNote,
                        onEvent = onEvent
                    )
                }

                Divider(
                    modifier = Modifier.height(1.dp),
                    color = colorResource(id = R.color.light_grey_app)
                )

                Column(modifier = Modifier.padding(spacing8)) {
                    NoteText(
                        note = if (noteScreenState.isNewNote) "" else noteScreenState.currentNote,
                        isNewNote = noteScreenState.isNewNote,
                        onEvent = onEvent
                    )
                }
            }

            AnimatedVisibility(noteScreenState.saveChangesDialog) {
                NotesAlertDialog(
                    title = noteScreenState.noteScreenDialogDetails.title,
                    message = noteScreenState.noteScreenDialogDetails.message,
                    neutralButtonText = null,
                    onDismiss = {
                        onEvent(NoteScreenEvent.DialogDismiss)
                    },
                    onConfirm = {
                        onEvent(NoteScreenEvent.DialogSave)
                        navController.navigate("Home")
                    },
                    onNeutral = {
                        navController.navigate("Home")
                    }
                )
            }
        },
        bottomBar = {
            noteScreenBottomAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(NoteScreenEvent.OnSaveClicked)
                    NLog.d("navController.popBackStack() = ${navController.popBackStack()}")
                    navController.navigate("home")
                },
                backgroundColor = colorResource(id = R.color.light_grey_app).copy(0.75f),
                shape = RoundedCornerShape(spacing16),
                contentColor = Color.White,
                modifier = Modifier
                    .width(80.dp)
                    .height(40.dp)
            ) {
                Text(text = "Save")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    )
}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    val navController = rememberNavController()
    NoteScreen(
        noteScreenState = NoteScreenState(false, mockNotesList().first(), saveChangesDialog = true),
        navController = navController,
        onEvent = {},
        noteScreenBottomAppBar = {},
        noteId = "0"
    )
}