package com.example.notesapp.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesapp.feature.Drawer
import com.example.notesapp.feature.DrawerScreens
import com.example.notesapp.ui.notesHomeScreen.state.NotesListingItemState
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.noteScreen.state.NoteScreenState
import com.example.notesapp.ui.noteScreen.view.NoteScreen
import com.example.notesapp.ui.noteScreen.view.bottomAppBarNoteScreen.NoteScreenBottomAppBar
import com.example.notesapp.ui.noteScreen.viewModel.NoteScreenViewModel
import com.example.notesapp.ui.notesHomeScreen.state.NotesHomeScreenState
import com.example.notesapp.ui.notesHomeScreen.view.NotesHomeScreen
import com.example.notesapp.ui.notesHomeScreen.view.NotesHomeScreenBottomAppBar
import com.example.notesapp.ui.notesHomeScreen.viewmodel.NotesHomeScreenViewModel
import com.example.notesapp.ui.theme.NotesAppTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreenMain(saveBitmap: (Pair<Bitmap, String>) -> Unit) {
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
                    composable(
                        DrawerScreens.Home.route,
                    ) {
                        val notesHomeScreenViewModel: NotesHomeScreenViewModel = hiltViewModel()
                        NotesHomeScreen(
                            notesHomeScreenViewModel.listingItemUIState,
                            notesHomeScreenViewModel.uiState,
                            notesHomeScreenViewModel::onEvent,
                            notesHomeScreenBottomAppBar = { NotesHomeScreenBottomAppBar(onEvent = notesHomeScreenViewModel::onEvent)},
//                            navController = navController
                        )

                        LaunchedEffect(Unit) {
                            notesHomeScreenViewModel.saveBitmap.collect { data ->
                                saveBitmap.invoke(data)
                            }
                        }
                    }
                    composable(
                        DrawerScreens.Note.route + "?noteId={noteId}",
                        arguments = listOf(navArgument("noteId") {
                            type = NavType.StringType
                            defaultValue = "Default"
                        })
                    ) {
                        val context = LocalContext.current
                        val noteScreenViewModel: NoteScreenViewModel = hiltViewModel()
                        val noteId = it.arguments?.getString("noteId") ?: ""
                        Log.d(TAG, "HomeScreenMain: $noteId")
                        NoteScreen(
                            noteScreenState = noteScreenViewModel.uiState,
                            onEvent = noteScreenViewModel::onEvent,
                            noteScreenBottomAppBar = { NoteScreenBottomAppBar(onEvent = noteScreenViewModel::onEvent) },
//                            navController = navController,
                            noteId = noteId
                        )

                        LaunchedEffect(Unit) {
                            noteScreenViewModel.displayToast.collect { message ->
                                Toast.makeText(
                                    context,
                                    message,
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenMain(saveBitmap = {})
}