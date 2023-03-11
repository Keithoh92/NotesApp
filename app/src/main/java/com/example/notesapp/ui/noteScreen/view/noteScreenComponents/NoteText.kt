package com.example.notesapp.ui.noteScreen.view.noteScreenComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.notesapp.R
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.noteScreen.event.NoteScreenEvent
import com.example.notesapp.ui.noteScreen.state.NoteScreenState

@Composable
fun NoteText(
    note: String,
    onEvent: (BaseComposeEvent) -> Unit
) {
    TextField(
        value = note,
        onValueChange = { onEvent(NoteScreenEvent.OnNoteChanged(it)) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}