package com.example.notesapp.feature.homescreen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.notesapp.feature.homescreen.data.NoteInfo
import com.example.notesapp.ui.theme.full
import com.example.notesapp.ui.theme.spacing10
import com.example.notesapp.ui.theme.spacing2
import com.example.notesapp.ui.theme.spacing4

// The row view for each note will be controlled by the settings that the user chooses

@Composable
fun NoteListItem(
    noteListing: NoteInfo,
    modifier: Modifier
) {
    Row() {

    }
}

@Composable
fun notesListingItem1(noteListing: NoteInfo) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            elevation = spacing10,
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing2)
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        start = spacing2,
                        top = spacing10,
                        bottom = spacing10),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = noteListing.id.toString(),
                    modifier = Modifier.weight(full),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}