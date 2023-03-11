package com.example.notesapp.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.ui.theme.NotesAppTheme

@Composable
fun TopAppBarHomeScreen(
//    title: String = "",
    onButtonClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "NoteZApp") },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            IconButton(
                onClick = { onButtonClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null
                )
            }
        },
        elevation = 10.dp,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopAppBarHomeScreenPreview() {
    NotesAppTheme {
        TopAppBarHomeScreen(
            onButtonClicked = {}
        )
    }
}
