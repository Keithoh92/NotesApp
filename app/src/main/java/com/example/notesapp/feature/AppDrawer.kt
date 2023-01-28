package com.example.notesapp.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.R
import com.example.notesapp.ui.theme.spacing24
import com.example.notesapp.ui.theme.spacing48

sealed class DrawerScreens(val title: String, val route: String) {
    object Home : DrawerScreens("Home", "home")
    object Account : DrawerScreens("Account",  "account")
    object Note : DrawerScreens("Note", "note")
}

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Account,
    DrawerScreens.Note
)

@Composable
fun Drawer(
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = spacing24, top = spacing48)
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "App Icon"
        )
        screens.forEach { screen ->
            Spacer(Modifier.height(spacing24))
            Text(
                text = screen.title,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .clickable {
                        onDestinationClicked(screen.route)
                    }
            )
        }

    }
}

@Preview
@Composable
fun DrawerPreview() {
    Drawer(
        onDestinationClicked = {}
    )
}