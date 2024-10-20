package com.example.notesapp.ui.notesHomeScreen.view.notesListItems

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.feature.DrawerScreens
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NoteItemNoteTextView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NoteItemTitleTextView
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NotesListItemCardView
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.mockData.mockNoteInfo
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NotesActionRow
import com.example.notesapp.ui.theme.*

const val ACTION_ITEM_SIZE = 56
const val CARD_OFFSET = 250f // we have 3 icons in a row, so that's 56 * 3

@Composable
fun NotesListItemOne(
    noteInfo: NoteInfo,
    isMinimised: Map<Int, Boolean>,
    isRevealedCardSwipe: Map<Int, Boolean>,
    onEvent: (BaseComposeEvent) -> Unit,
    navController: NavController
) {

    val minimised = isMinimised[noteInfo.id]
    val revealed = isRevealedCardSwipe[noteInfo.id] ?: false
    
    Box(modifier = Modifier.fillMaxWidth()) {
        NotesActionRow(actionIconSize = iconSize32, onDelete = { onEvent(NotesHomeScreenEvent.OnClickNoteDelete(noteInfo.id)) }, onEdit = { /*TODO*/ }, onFavorite = {})
        NotesListItemCardView(
            onClick = { onEvent(NotesHomeScreenEvent.OnNoteClicked(noteId = noteInfo.id)) },
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
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NoteItemTitleTextView(title = noteInfo.title)

                    Spacer(modifier = Modifier.weight(full))

                    if (noteInfo.category?.isNotEmpty() == true) {
                        Text(
                            text = noteInfo.category.toString(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                    }

                    IconButton(onClick = {
                        onEvent(NotesHomeScreenEvent.OnMinimiseNote(noteInfo.id, minimised))
                    }) {
                        if (minimised == true) {
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = null

                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.ArrowDropUp,
                                contentDescription = null
                            )
                        }
                    }
                }

                AnimatedVisibility (minimised != true) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        NoteItemNoteTextView(
                            title = noteInfo.title,
                            note = noteInfo.note,
                            onEvent = onEvent
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListItemOnePreview() {
    val navController = rememberNavController()
    NotesAppTheme {
        NotesListItemOne(
            noteInfo = mockNoteInfo().first(),
            isMinimised = emptyMap(),
            isRevealedCardSwipe = mapOf(),
            onEvent = {},
            navController = navController,
        )
    }
}