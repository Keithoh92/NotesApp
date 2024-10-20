package com.example.notesapp.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.notesapp.R
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.spacing16
import com.example.notesapp.ui.theme.spacing8

@Composable
fun NotesAlertDialog(
    title: String?,
    message: String?,
    @StringRes confirmButtonText: Int = R.string.ok,
    @StringRes dismissButtonText: Int = R.string.cancel,
    @StringRes neutralButtonText: Int?,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onNeutral: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss,
        DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing16),
            shape = MaterialTheme.shapes.medium,
            backgroundColor = colorResource(id = R.color.darker_grey_app)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing8),
                modifier = Modifier.padding(spacing16)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (title != null) {
                        Text(text = title, color = colorResource(id = R.color.orange_app))
                    }
                }

                Row {
                    if (message != null) {
                        Text(text = message, color = Color.White)
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (neutralButtonText != null) {
                        TextButton(onClick = onNeutral) {
                            Text(text = stringResource(id = neutralButtonText), color = Color.White)
                        }
                    }

                    TextButton(onClick = onDismiss) {
                        Text(text = stringResource(id = dismissButtonText), color = Color.White)
                    }

                    TextButton(onClick = onConfirm) {
                        Text(text = stringResource(id = confirmButtonText), color = Color.White)
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesAlertDialogPreview() {
    NotesAppTheme {
        NotesAlertDialog(
            title = stringResource(R.string.note_changed),
            message = stringResource(R.string.note_changed_message),
            confirmButtonText = R.string.save,
            neutralButtonText = R.string.discard,
            onDismiss = {},
            onConfirm = {},
            onNeutral = {}
        )
    }
}