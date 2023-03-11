package com.example.notesapp.ui

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.feature.Drawer
import com.example.notesapp.feature.DrawerScreens
import com.example.notesapp.ui.notesHomeScreen.state.NotesListingItemState
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.noteScreen.state.NoteScreenState
import com.example.notesapp.ui.noteScreen.view.NoteScreen
import com.example.notesapp.ui.noteScreen.view.bottomAppBarNoteScreen.NoteScreenBottomAppBar
import com.example.notesapp.ui.notesHomeScreen.state.NotesHomeScreenState
import com.example.notesapp.ui.notesHomeScreen.view.NotesHomeScreen
import com.example.notesapp.ui.notesHomeScreen.view.NotesHomeScreenBottomAppBar
import com.example.notesapp.ui.theme.NotesAppTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreenMain(
    notesListingItemState: NotesListingItemState,
    notesHomeScreenState: NotesHomeScreenState,
    noteScreenState: NoteScreenState,
    onEvent: (BaseComposeEvent) -> Unit
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = { scope.launch { drawerState.open() } }

    NotesAppTheme {
        Scaffold(
            topBar = { TopAppBarHomeScreen(onButtonClicked = { openDrawer() }) },
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            popUpTo = navController.graph.startDestinationId
                            launchSingleTop = true
                        }
                    }
                )
            },
            content = {
                NavHost(
                    navController = navController,
                    startDestination = DrawerScreens.Home.route
                ) {
                    composable(DrawerScreens.Home.route) {
                        NotesHomeScreen(
                            notesListingItemState,
                            notesHomeScreenState,
                            onEvent,
                            notesHomeScreenBottomAppBar = { NotesHomeScreenBottomAppBar(onEvent = onEvent)},
                            navController = navController
                        )
                    }
                    composable(DrawerScreens.Note.route) {
                        NoteScreen(
                            noteScreenState = noteScreenState,
                            onEvent = onEvent,
                            noteScreenBottomAppBar = { NoteScreenBottomAppBar(onEvent = onEvent) }
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenMain(
        notesListingItemState = NotesListingItemState(),
        notesHomeScreenState = NotesHomeScreenState(),
        noteScreenState = NoteScreenState(),
        onEvent = {}
    )
}