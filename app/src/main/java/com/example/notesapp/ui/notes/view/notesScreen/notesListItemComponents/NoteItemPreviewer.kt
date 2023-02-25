package com.example.notesapp.ui.notes.view.notesScreen.notesListItemComponents

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.notesapp.ui.theme.spacing16
import com.example.notesapp.ui.theme.spacing8

@SuppressLint("ResourceType")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteItemPreviewer(
    title: String,
    @StringRes image: Int?,
    note: String?
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = {}
    ) {
        Card(
            modifier = Modifier.padding(spacing16),
            border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray
            ),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing8),
                modifier = Modifier.padding(spacing16)
            ) {
                NoteItemTitleTextView(title = title)

                Divider(color = Color.Gray, thickness = 1.dp)

                if (image != null) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(spacing8).align(Alignment.CenterHorizontally)
                    )
                } else {
                    Text(
                        text = note.toString(),
                        Modifier
                            .background(Color.White)
                            .padding(spacing8),
                        color = Color.Black
                    )
                }
            }
        }
    }
}