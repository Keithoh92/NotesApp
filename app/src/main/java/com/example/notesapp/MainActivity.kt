package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.ui.notes.data.NotesListingItemState
import com.example.notesapp.ui.notes.data.NotesScreenState
import com.example.notesapp.ui.notes.view.HomeScreenMain
import com.example.notesapp.ui.notes.viewmodel.NotesScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notesScreenViewModel: NotesScreenViewModel by viewModels()

        setContent {
            HomeScreenMain(
                notesListingItemState = notesScreenViewModel.notesListingItemState,
                notesScreenState = notesScreenViewModel.notesScreenState,
                onEvent = notesScreenViewModel::onEvent
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    HomeScreenMain(
        notesListingItemState = NotesListingItemState(),
        notesScreenState = NotesScreenState(),
        onEvent = {}
    )
}