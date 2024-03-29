package com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.theme.full
import com.example.notesapp.ui.theme.spacing16

@SuppressLint("CheckResult")
@Composable
fun GridViewGalleryTwoByTwo(
    title: String,
    images: List<String>,
    onEvent: (BaseComposeEvent) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Filled.ArrowLeft,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .weight(0.09f)
        )

        LazyHorizontalGrid(
            horizontalArrangement = Arrangement.spacedBy(spacing16),
            rows = GridCells.Fixed(1),
            modifier = Modifier
                .height(201.dp)
                .weight(full)
        ) {
            items(images.size) { item ->
                val painter = rememberAsyncImagePainter(images[item])
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(100.dp)
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
                        }
                )
            }
        }

        Icon(
            imageVector = Icons.Filled.ArrowRight,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .weight(0.09f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GridViewGalleryTwoByTwoPreview() {
    GridViewGalleryTwoByTwo(title = "Note 1", images = emptyList(), onEvent = {})
}