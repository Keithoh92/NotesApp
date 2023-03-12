package com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.iconSize48
import com.example.notesapp.ui.theme.spacing2

@Composable
fun VerticalGalleryLazyRow(
    title: String,
    images: List<String>,
    onEvent: (BaseComposeEvent) -> Unit
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        items(images.size) { item ->
            val painter = rememberAsyncImagePainter(model = images[item])
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(iconSize48)
                    .clip(RoundedCornerShape(10.dp))
                    .border(BorderStroke(width = 1.dp, color = Color.DarkGray))
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                onEvent(NotesHomeScreenEvent.OnLongPressImage(title, images[item]))
                            },
                            onPress = {
                                awaitRelease()
                                onEvent(NotesHomeScreenEvent.OnReleaseLongPressImage)
                            }
                        )
                    },
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
            title = "Note 1",
            images = emptyList(),
            onEvent = {}
        )
    }
}