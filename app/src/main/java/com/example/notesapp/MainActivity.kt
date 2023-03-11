package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.ui.notesHomeScreen.state.NotesListingItemState
import com.example.notesapp.ui.notesHomeScreen.state.NotesHomeScreenState
import com.example.notesapp.ui.HomeScreenMain
import com.example.notesapp.ui.noteScreen.state.NoteScreenState
import com.example.notesapp.ui.noteScreen.viewModel.NoteScreenViewModel
import com.example.notesapp.ui.notesHomeScreen.viewmodel.NotesHomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notesHomeScreenViewModel: NotesHomeScreenViewModel by viewModels()
        val noteScreenViewModel: NoteScreenViewModel by viewModels()

        setContent {
            HomeScreenMain(
                notesListingItemState = notesHomeScreenViewModel.notesListingItemState,
                notesHomeScreenState = notesHomeScreenViewModel.notesHomeScreenState,
                noteScreenState = noteScreenViewModel.noteScreenState,
                onEvent = notesHomeScreenViewModel::onEvent
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    HomeScreenMain(
        notesListingItemState = NotesListingItemState(),
        notesHomeScreenState = NotesHomeScreenState(),
        noteScreenState = NoteScreenState(),
        onEvent = {}
    )
}