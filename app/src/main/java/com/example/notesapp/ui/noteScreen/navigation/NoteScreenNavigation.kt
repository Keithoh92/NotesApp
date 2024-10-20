package com.example.notesapp.ui.noteScreen.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notesapp.ui.noteScreen.view.NoteScreen
import com.example.notesapp.ui.noteScreen.view.NoteScreenMain
import com.example.notesapp.ui.noteScreen.viewModel.NoteScreenViewModel

const val noteScreenRoute = "note_screen_route"
const val noteScreenArgs = "note_screen_args"

fun NavController.navigateToNoteScreen(
    noteId: Int,
    navOptions: NavOptions? = null
) {
    this.navigate("$noteScreenRoute/${noteScreenArgs}", navOptions)
}

fun NavGraphBuilder.propertyDetailsScreen(onBack: () -> Unit) {
    composable(
        route = "$noteScreenRoute/{$noteScreenArgs}",
        arguments = listOf(navArgument(noteScreenArgs) { type = NavType.IntType })
    ) { navBackStackEntry ->
        val viewModel = hiltViewModel<NoteScreenViewModel>()
        val noteId = navBackStackEntry.arguments?.getInt(noteScreenArgs)
        NoteScreenMain(viewModel = viewModel, noteId = noteId!!)
    }
}