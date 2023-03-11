package com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent

@Composable
fun NoteItemNoteTextView(
    title: String,
    note: String,
    onEvent: (BaseComposeEvent) -> Unit
) {
    Text(
        text = note,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis,
        color = Color.White,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        onEvent(NotesHomeScreenEvent.OnLongPressNote(title, note))
                    },
                    onPress = {
                        awaitRelease()
                        onEvent(NotesHomeScreenEvent.OnReleaseLongPressNote)
                    }
                )
            }
    )
}