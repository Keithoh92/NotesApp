package com.example.notesapp.ui.notes.view.notesScreen.notesListItemComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.ui.notes.data.mockListOfImages
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.iconSize48
import com.example.notesapp.ui.theme.spacing2

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
                    .clip(RoundedCornerShape(10.dp))
                    .border(BorderStroke(width = 1.dp, color = Color.DarkGray)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(horizontal = spacing2))
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