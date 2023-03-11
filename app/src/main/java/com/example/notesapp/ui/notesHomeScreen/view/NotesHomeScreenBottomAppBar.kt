package com.example.notesapp.ui.notesHomeScreen.view

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.spacing10

@Composable
fun NotesHomeScreenBottomAppBar(
    onEvent: (BaseComposeEvent) -> Unit
    ) {
    BottomAppBar(
        elevation = spacing10,
        backgroundColor = MaterialTheme.colors.primary,
        cutoutShape = CircleShape
    ) {
        val listOfBottomAppBarItems = prepareBottomAppBarItems()

        for (item in listOfBottomAppBarItems.indices) {
            IconButton(onClick = {
                when (item) {
                    0 -> onEvent(NotesHomeScreenEvent.OnClickSearch)
                    1 -> onEvent(NotesHomeScreenEvent.OnClickShare)
                    else -> onEvent(NotesHomeScreenEvent.OnClickNotifications)
                }
            }) {
                Icon(
                    imageVector = listOfBottomAppBarItems[item].icon,
                    contentDescription = listOfBottomAppBarItems[item].label
                )
            }
        }
    }
}

fun prepareBottomAppBarItems(): List<BottomMenuItem> {
    val bottomMenuItemList = arrayListOf<BottomMenuItem>()

    bottomMenuItemList.add(BottomMenuItem(label = "Search", icon = Icons.Filled.Search))
    bottomMenuItemList.add(BottomMenuItem(label = "Share", icon = Icons.Filled.Share))
    bottomMenuItemList.add(
        BottomMenuItem(
            label = "Notifications",
            icon = Icons.Filled.Notifications
        )
    )

    return bottomMenuItemList
}

@Preview(showBackground = true)
@Composable
fun NotesHomeScreenBottomAppBarPreview() {
    NotesAppTheme {
        NotesHomeScreenBottomAppBar(
            onEvent = {}
        )
    }
}