package com.example.notesapp.feature.notes.view.notesScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.R
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.spacing10

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesScreenBottomAppBar(
    onClickSearch: () -> Unit,
    onClickShare: () -> Unit,
    onClickNotifications: () -> Unit
) {
    BottomAppBar(
        elevation = spacing10,
        backgroundColor = colorResource(id = R.color.dark_grey_app),
        cutoutShape = CircleShape
    ) {
        val listOfBottomAppBarItems = prepareBottomAppBarItems()

        for (item in listOfBottomAppBarItems.indices) {
            IconButton(onClick = {
                when (item) {
                    0 -> onClickSearch()
                    1 -> onClickShare()
                    else -> onClickNotifications()
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
            onClickSearch = {},
            onClickShare = {}
        ) {

        }
    }
}