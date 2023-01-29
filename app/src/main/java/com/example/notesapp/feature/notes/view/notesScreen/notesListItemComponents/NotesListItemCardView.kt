package com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.ui.theme.*

@Composable
fun NotesListItemCardView(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        elevation = spacing10,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        ),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun NotesListItemCardViewPreview() {
    NotesAppTheme {
        NotesListItemCardView(onClick = {}, content = {})
    }
}