package com.example.notesapp.ui.notesHomeScreen.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.notesapp.ui.notesHomeScreen.view.NotesHomeScreenMain
import com.example.notesapp.ui.notesHomeScreen.viewmodel.NotesHomeScreenViewModel

const val notesHomeScreenRoute = "notes_home_screen_route"

fun NavGraphBuilder.notesHomeScreen() {
    composable(route = notesHomeScreenRoute) {
        val viewModel = hiltViewModel<NotesHomeScreenViewModel>()
        NotesHomeScreenMain(viewModel = viewModel)
    }
}