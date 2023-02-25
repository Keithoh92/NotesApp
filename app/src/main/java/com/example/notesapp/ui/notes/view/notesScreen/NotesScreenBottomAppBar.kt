package com.example.notesapp.ui.notes.view.notesScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.event.NotesAppEvent
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.spacing10

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesScreenBottomAppBar(
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
                    0 -> onEvent(NotesAppEvent.OnClickSearch)
                    1 -> onEvent(NotesAppEvent.OnClickShare)
                    else -> onEvent(NotesAppEvent.OnClickNotifications)
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
fun BottomAppBarAllPreview() {
    NotesAppTheme {
        NotesScreenBottomAppBar(
            onEvent = {}
        )
    }
}