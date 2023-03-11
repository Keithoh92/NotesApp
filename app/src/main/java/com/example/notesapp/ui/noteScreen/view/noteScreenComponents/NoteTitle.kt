package com.example.notesapp.ui.noteScreen.view.noteScreenComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.R
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.noteScreen.event.NoteScreenEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteTitle(
    title: String,
    onEvent: (BaseComposeEvent) -> Unit
) {
    var value by remember {
        mutableStateOf(title)
    }

    val keyBoardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = {
//            onEvent(NoteScreenEvent.OnTitleChanged(it))
            value = it
        },
        label = { Text(text = stringResource(id = R.string.note_title), color = Color.White) },
        placeholder = { Text(text = stringResource(id = R.string.note_title)) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyBoardController?.hide() }
        )
    )
}

@Preview
@Composable
fun NoteTitlePreview() {
    NoteTitle(title = "Test", onEvent = {})
}