package com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun NoteItemNoteTextView(
    title: String,
    note: String,
    onLongPressNote: (String, String) -> Unit,
    onReleaseLongPressNote: () -> Unit
) {
    Text(
        text = note,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        onLongPressNote(title, note)
                    },
                    onPress = {
                        awaitRelease()
                        onReleaseLongPressNote()
                    }
                )
            }
    )
}