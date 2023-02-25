package com.example.notesapp.ui.notes.view

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.feature.Drawer
import com.example.notesapp.feature.DrawerScreens
import com.example.notesapp.ui.notes.data.NotesListingItemState
import com.example.notesapp.ui.notes.view.notesScreen.NotesScreen
import com.example.notesapp.ui.event.BaseComposeEvent
import kotlinx.coroutines.launch

@Composable
fun HomeScreenMain(
    notesListingItemState: NotesListingItemState,
    onEvent: (BaseComposeEvent) -> Unit
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = { scope.launch { drawerState.open() } }

//    NotesAppTheme {
    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
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
    ) {
        NavHost(
            navController = navController,
            startDestination = DrawerScreens.Home.route
        ) {
            composable(DrawerScreens.Home.route) {
                NotesScreen(
                    notesListingItemState,
                    onEvent,
                    openDrawer = {
                        openDrawer()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenMain(
        notesListingItemState = NotesListingItemState(),
        onEvent = {}
    )
}