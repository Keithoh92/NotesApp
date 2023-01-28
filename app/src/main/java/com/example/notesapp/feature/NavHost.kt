package com.example.notesapp.feature

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import com.example.notesapp.feature.homescreen.view.DummyHomeScreen

@Composable
fun NotesAppNavigationHost(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = DrawerScreens.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(DrawerScreens.Home.route) {
//            NotesScreen(
//                openDrawer = {
//
//                }
//            )
        }
    }
}