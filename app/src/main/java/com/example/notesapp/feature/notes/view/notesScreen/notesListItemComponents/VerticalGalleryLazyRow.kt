package com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.feature.notes.data.mockListOfImages
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.iconSize48

@Composable
fun VerticalGalleryLazyRow(
    images: List<Int>
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        items(images.size) { item ->
            Image(
                painter = painterResource(id = images[item]),
                contentDescription = null,
                modifier = Modifier
                    .size(iconSize48)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerticalGalleryLazyRowPreview() {
    NotesAppTheme {
        VerticalGalleryLazyRow(
            images = mockListOfImages()
        )
    }
}