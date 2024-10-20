package com.example.notesapp.ui.noteScreen.view.noteScreenComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.noteScreen.event.NoteScreenEvent
import com.example.notesapp.ui.theme.spacing8

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteText(
    note: String,
    isNewNote: Boolean,
    onEvent: (BaseComposeEvent) -> Unit
) {

    var value by remember {
        mutableStateOf(note)
    }

    if (value != note && !isNewNote) {
        value = note
    }

    val focusRequester = remember { FocusRequester() }
    val keyBoardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.padding(spacing8)) {
        BasicTextField(
            value = value,
            onValueChange = {
                onEvent(NoteScreenEvent.OnNoteChanged(it))
                value = it
            },
            modifier = Modifier
                .focusRequester(focusRequester),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            cursorBrush = SolidColor(Color.White)
        )
    }

    LaunchedEffect(
        key1 = Unit,
    ) {
        focusRequester.requestFocus()
        keyBoardController?.hide()
    }

}