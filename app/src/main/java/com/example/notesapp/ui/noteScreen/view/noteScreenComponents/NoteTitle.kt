package com.example.notesapp.ui.noteScreen.view.noteScreenComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.R
import com.example.notesapp.common.logs.NLog
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.noteScreen.event.NoteScreenEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteTitle(
    title: String,
    isNewNote: Boolean,
    onEvent: (BaseComposeEvent) -> Unit
) {
    var value by remember {
        mutableStateOf(title)
    }

    if (value != title && !isNewNote) {
        value = title
    }

    val focusRequester = remember { FocusRequester() }
    val keyBoardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = value,
        onValueChange = {
            NLog.d("onValueChanged = $it")
            onEvent(NoteScreenEvent.OnTitleChanged(it))
            value = it
        },
        label = { Text(text = stringResource(id = R.string.note_title_placeholder), color = Color.White) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.note_title_placeholder),
                color = Color.LightGray
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
        ),
        textStyle = LocalTextStyle.current.copy(color = Color.White),
        keyboardActions = KeyboardActions(
            onDone = { keyBoardController?.hide() }
        )
    )

    LaunchedEffect(
        key1 = Unit,
    ) {
        focusRequester.requestFocus()
        keyBoardController?.hide()
    }
}

@Preview
@Composable
fun NoteTitlePreview() {
    NoteTitle(
        title = "Test",
        isNewNote = true,
        onEvent = {}
    )
}