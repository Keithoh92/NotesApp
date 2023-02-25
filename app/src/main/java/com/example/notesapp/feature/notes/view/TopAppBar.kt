package com.example.notesapp.feature.notes.view

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.R
import com.example.notesapp.ui.theme.NotesAppTheme

@Composable
fun TopAppBarHomeScreen(
//    title: String = "",
    onButtonClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "NoteZApp") },
        backgroundColor = colorResource(id = R.color.dark_grey_app),
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
