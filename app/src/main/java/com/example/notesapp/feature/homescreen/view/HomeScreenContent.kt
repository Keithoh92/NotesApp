package com.example.notesapp.feature.homescreen.view

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.notesapp.feature.homescreen.data.NoteInfo
import com.example.notesapp.feature.homescreen.data.NotesListingState
import com.example.notesapp.ui.theme.fontSize20
import com.example.notesapp.ui.theme.spacing10
import com.example.notesapp.ui.theme.spacing2
import com.example.notesapp.ui.theme.spacing4

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreenContent(
    noteListing: NoteInfo,
    modifier: Modifier = Modifier,
    notesPageName: String = "My Notes"
) {

    val state by mutableStateOf(NotesListingState())

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(spacing10)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(spacing4)
        ) {
            Text(
                text = notesPageName,
                fontSize = fontSize20,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = modifier.height(spacing4))

        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(spacing2)
        ) {
            LazyColumn(
                modifier = modifier.fillMaxWidth()
            ) {
                items(state.notes.size) { i ->
                    val note = state.notes[i]

                }
            }
        }
    }
}