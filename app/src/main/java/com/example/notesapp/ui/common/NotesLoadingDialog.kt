package com.example.notesapp.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.size20
import com.example.notesapp.ui.theme.size24
import com.example.notesapp.ui.theme.size32
import com.example.notesapp.ui.theme.size36
import com.example.notesapp.ui.theme.size4

@Composable
fun NotesLoadingDialog(progressMessage: String) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            elevation = size4,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        PaddingValues(
                            horizontal = size32,
                            vertical = size24
                        )
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    space = size20,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NotesProgressIndicator(indicatorSize = size36)
                Text(text = progressMessage)
            }
        }
    }
}

@Preview("Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NotesLoadingDialogPreview() {
    NotesAppTheme() {
        NotesLoadingDialog(progressMessage = "Please wait...")
    }
}