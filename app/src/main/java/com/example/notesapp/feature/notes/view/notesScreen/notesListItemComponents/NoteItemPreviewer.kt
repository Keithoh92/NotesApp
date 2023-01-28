package com.example.notesapp.feature.notes.view.notesScreen.notesListItemComponents

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@SuppressLint("ResourceType")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteItemPreviewer(
    @StringRes image: Int?,
    note: String?
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = {}
    ) {
        if (image != null) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Text(
                text = note.toString(),
                Modifier.background(Color.White),
                color = Color.Black
            )
        }
    }
}