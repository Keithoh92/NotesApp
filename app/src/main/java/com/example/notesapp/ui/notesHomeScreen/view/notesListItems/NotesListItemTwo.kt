package com.example.notesapp.ui.notesHomeScreen.view.notesListItems

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.mockData.mockNoteInfo
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NoteItemNoteTextView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NoteItemTitleTextView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NotesActionRow
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NotesListItemCardView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.VerticalGalleryLazyRow
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.full
import com.example.notesapp.ui.theme.iconSize32
import com.example.notesapp.ui.theme.spacing16
import com.example.notesapp.ui.theme.spacing8

@Composable
fun NotesListItemTwo(
    noteInfo: NoteInfo,
    isRevealedCardSwipe: Map<Int, Boolean>,
    onEvent: (BaseComposeEvent) -> Unit,
    navController: NavController,
    shouldLoadPlaceholderWelcomeImages: Boolean,
) {
    val revealed = isRevealedCardSwipe[noteInfo.id] ?: false

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        NotesActionRow(
            actionIconSize = iconSize32,
            onDelete = { onEvent(NotesHomeScreenEvent.OnClickNoteDelete(noteInfo.id)) },
            onEdit = { /*TODO*/ },
            onFavorite = {}
        )
        NotesListItemCardView(
            onClick = { navController.navigate("Note/?noteId=${noteInfo.id}") },
            noteId = noteInfo.id,
            isRevealed = revealed,
            cardOffset = CARD_OFFSET,
            onEvent = onEvent
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(spacing16)
            ) {
                Row {
                    NoteItemTitleTextView(title = noteInfo.title)

                    Spacer(
                        modifier = Modifier
                            .padding(start = spacing8)
                            .weight(full)
                    )

                    if (noteInfo.noteImages?.isNotEmpty() == true || shouldLoadPlaceholderWelcomeImages) {
                        VerticalGalleryLazyRow(
                            title = noteInfo.title,
                            images = noteInfo.noteImages ?: emptyList(),
                            onEvent = onEvent
                        )
                    }
                }

                Row(modifier = Modifier.padding(top = spacing8)) {
                    NoteItemNoteTextView(
                        title = noteInfo.title,
                        note = noteInfo.note,
                        onEvent
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListItemTwoPreview() {
    val navController = rememberNavController()
    NotesAppTheme {
        NotesListItemTwo(
            noteInfo = mockNoteInfo()[2],
            isRevealedCardSwipe = mapOf(),
            onEvent = {},
            navController = navController,
            shouldLoadPlaceholderWelcomeImages = false
        )
    }
}