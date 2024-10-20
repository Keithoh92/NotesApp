package com.example.notesapp.ui.notesHomeScreen.view.notesListItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NoteItemNoteTextView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NoteItemTitleTextView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.GridViewGalleryTwoByTwo
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NotesListItemCardView
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.mockData.mockNoteInfo
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.full
import com.example.notesapp.ui.theme.spacing16

@Composable
fun NotesListItemSideBySide(
    noteInfo: NoteInfo,
    onClick: () -> Unit,
    isRevealedCardSwipe: Map<Int, Boolean>,
    onEvent: (BaseComposeEvent) -> Unit
) {
    val revealed = isRevealedCardSwipe[noteInfo.id] ?: false

    NotesListItemCardView(
        onClick = { onEvent(NotesHomeScreenEvent.OnNoteClicked(noteId = noteInfo.id)) },
        noteId = noteInfo.id,
        isRevealed = revealed,
        cardOffset = CARD_OFFSET,
        onEvent = onEvent
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(spacing16)
        ) {

            Column(
                modifier = Modifier
                    .weight(full)
            ) {
                NoteItemTitleTextView(title = noteInfo.title)

                NoteItemNoteTextView(
                    title = noteInfo.title,
                    note = noteInfo.note,
                    onEvent = onEvent
                )
            }

            Column(
                modifier = Modifier
                    .weight(full)
            ) {
                GridViewGalleryTwoByTwo(
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
fun NotesListItemSideBySidePreview() {
    NotesAppTheme {
        NotesListItemSideBySide(
            noteInfo = mockNoteInfo()[3],
            onClick = {},
            isRevealedCardSwipe = mapOf(),
            onEvent = {}
        )
    }
}