package com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.iconSize32
import com.example.notesapp.ui.theme.spacing10

@Composable
fun NotesActionRow(
    actionIconSize: Dp,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onFavorite: () -> Unit
) {
    Column(modifier = Modifier.padding(end = spacing10)) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(
                modifier = Modifier.size(actionIconSize),
                onClick = onDelete,
                content = {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        tint = Color.Gray,
                        contentDescription = "delete action",
                    )
                }
            )
            Text(text = "Delete")
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(
                modifier = Modifier.size(actionIconSize),
                onClick = onEdit,
                content = {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        tint = Color.Gray,
                        contentDescription = "edit action",
                    )
                },
            )
            Text(text = "Edit")
        }

//        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
//            IconButton(
//                modifier = Modifier.size(actionIconSize),
//                onClick = onFavorite,
//                content = {
//                    Icon(
//                        imageVector = Icons.Filled.Favorite,
//                        tint = Color.Red,
//                        contentDescription = "Expandable Arrow",
//                    )
//                }
//            )
//            Text(text = "Favourite")
//        }
    }
}

@Preview
@Composable
fun NotesActionRowPreview() {
    NotesAppTheme {
        NotesActionRow(
            actionIconSize = iconSize32,
            onDelete = { /*TODO*/ },
            onEdit = { /*TODO*/ },
            onFavorite = {}
        )
    }

}