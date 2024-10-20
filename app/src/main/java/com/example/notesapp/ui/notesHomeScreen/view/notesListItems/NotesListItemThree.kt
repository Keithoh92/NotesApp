package com.example.notesapp.ui.notesHomeScreen.view.notesListItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.mockData.mockNoteInfo
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NoteItemTitleTextView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NotesListItemCardView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.VerticalGalleryLazyRow
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.spacing10
import com.example.notesapp.ui.theme.spacing16

@Composable
fun NotesListItemThree(
    noteInfo: NoteInfo,
    onClick: () -> Unit,
    isRevealedCardSwipe: Map<Int, Boolean>,
    onEvent: (BaseComposeEvent) -> Unit,
    navController: NavController
) {

    val revealed = isRevealedCardSwipe[noteInfo.id] ?: false

    NotesListItemCardView(
        onClick = { navController.navigate("Note/?noteId=${noteInfo.id}") },
        noteId = noteInfo.id,
        isRevealed = revealed,
        cardOffset = CARD_OFFSET,
        onEvent = onEvent
    ) {
         Column(
             verticalArrangement = Arrangement.spacedBy(spacing10),
             horizontalAlignment = Alignment.Start,
             modifier = Modifier.padding(spacing16).fillMaxWidth()
         ) {
             NoteItemTitleTextView(title = noteInfo.title)

             if (noteInfo.noteImages?.isNotEmpty() == true) {
                 VerticalGalleryLazyRow(
                     title = noteInfo.title,
                     images = noteInfo.noteImages ?: emptyList(),
                     onEvent = onEvent
                 )
             }
         }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListItemThreePreview() {
    val navController = rememberNavController()
    NotesAppTheme {
        NotesListItemThree(
            noteInfo = mockNoteInfo()[3],
            onClick = {},
            isRevealedCardSwipe = mapOf(),
            onEvent = {},
            navController = navController
        )
    }
}