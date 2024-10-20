package com.example.notesapp.ui.noteScreen.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.notesapp.ui.noteScreen.viewModel.NoteScreenViewModel

@Composable
fun NoteScreenMain(viewModel: NoteScreenViewModel, noteId: Int) {

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchOpenedNote(noteId)
    })
    NoteScreen(
        noteScreenState = viewModel.uiState,
        onEvent = viewModel::onEvent,
        noteScreenBottomAppBar = { /*TODO*/ })
}