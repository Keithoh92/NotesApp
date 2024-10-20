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
import com.example.notesapp.R
import com.example.notesapp.common.logs.NLog
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
        val imagesToShow = images.ifEmpty { getPlaceHolderDrawables() }

        items(imagesToShow.size) { item ->
            NLog.d("image -> ${imagesToShow[item]}")
            val painter = rememberAsyncImagePainter(model = imagesToShow[item])
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
                                onEvent(NotesHomeScreenEvent.OnLongPressImage(title, painter))
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

fun getPlaceHolderDrawables() : List<Int> {
    return listOf(
        R.drawable.images_2,
        R.drawable.beach_resort_sunset_hd_wallpaper_background_jpg,
        R.drawable.images_3,
        R.drawable.images_4,
        R.drawable._958474,
        R.drawable.istockphoto_1212174159_612x612,
        R.drawable.photo_1533450718592_29d45635f0a9,
    )
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