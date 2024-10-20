package com.example.notesapp.ui.notesHomeScreen.view

import androidx.compose.runtime.Composable
import com.example.notesapp.ui.notesHomeScreen.viewmodel.NotesHomeScreenViewModel

@Composable
fun NotesHomeScreenMain(viewModel: NotesHomeScreenViewModel) {

//    LaunchedEffect(key1 = Unit, block = {
//
//    })

    NotesHomeScreen(
        notesListingItemState = viewModel.listingItemUIState,
        notesHomeScreenState = viewModel.uiState,
        onEvent = viewModel::onEvent,
        notesHomeScreenBottomAppBar = { /*TODO*/ }
    )
}