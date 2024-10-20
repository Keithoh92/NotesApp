package com.example.notesapp.ui.notesHomeScreen.view

import android.content.ContentValues
import android.media.Image
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.notesapp.R
import com.example.notesapp.common.logs.NLog
import com.example.notesapp.feature.DrawerScreens
import com.example.notesapp.ui.common.NotesAlertDialog
import com.example.notesapp.ui.common.NotesLoadingDialog
import com.example.notesapp.ui.notesHomeScreen.state.NotesListingItemState
import com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents.NoteItemPreviewer
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.notesHomeScreen.state.NotesHomeScreenState
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.spacing16
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NotesHomeScreen(
    notesListingItemState: StateFlow<NotesListingItemState>,
    notesHomeScreenState: StateFlow<NotesHomeScreenState>,
    onEvent: (BaseComposeEvent) -> Unit,
    notesHomeScreenBottomAppBar: @Composable () -> Unit
) {

    val uiState by notesHomeScreenState.collectAsState()
    val listingItemUIState by notesListingItemState.collectAsState()

    if (uiState.noteClicked.second) {
        Log.d(ContentValues.TAG, "NotesListItemTwo: ${uiState.noteClicked.first}")

        navController.navigate("note?noteId=${uiState.noteClicked.first}") {
            popUpTo("home") { inclusive = true }
        }
        onEvent(NotesHomeScreenEvent.OnNoteOpened)
    }

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = it.calculateBottomPadding(),
                        top = it.calculateTopPadding()
                    )
                    .background(MaterialTheme.colors.primaryVariant)
            ) {
                NotesList(
                    notesList = notesHomeScreenState.notes,
                    notesListingItemState = notesListingItemState,
                    notesHomeScreenState,
                    onEvent = onEvent,
                    navController = navController
                )

                AnimatedVisibility(
                    visible = notesListingItemState.showNoteItemPreview,
                    enter = fadeIn(animationSpec = tween(500))
                ) {
                    if (notesListingItemState.imageToShow != null) {
                        Image(
                            painter = notesListingItemState.imageToShow,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        NoteItemPreviewer(
                            title = notesListingItemState.noteTitle,
                            note = notesListingItemState.noteToShow
                        )
                    }
                }
                
                AnimatedVisibility(visible = notesHomeScreenState.deleteNoteDialog) {
                    NotesAlertDialog(
                        title = notesHomeScreenState.noteScreenDialogDetails.title,
                        message = notesHomeScreenState.noteScreenDialogDetails.message,
                        neutralButtonText = null,
                        onDismiss = { onEvent(NotesHomeScreenEvent.OnCancelDialogDeleteNote) },
                        onConfirm = { onEvent(NotesHomeScreenEvent.OnConfirmDialogDeleteNote(notesHomeScreenState.noteScreenDialogDetails.noteId)) }) {
                        
                    }
                }
                
                AnimatedVisibility(visible = notesHomeScreenState.isLoading) {
                    NotesLoadingDialog(progressMessage = stringResource(R.string.loading_notes))
                }
            }
        },
        bottomBar = { notesHomeScreenBottomAppBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("note?noteId=0") {
                        popUpTo("home") { inclusive = true }
                        NLog.d("Are We Working lads?")
                    }
                },
                backgroundColor = colorResource(id = R.color.light_grey_app).copy(0.75f),
                shape = CircleShape,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true,
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

@Preview(showBackground = true)
@Composable
fun NotesHomeScreenPreview() {
    val navController = rememberNavController()
    NotesAppTheme {
        NotesHomeScreen(
            notesListingItemState = NotesListingItemState(),
            notesHomeScreenState = NotesHomeScreenState(),
            notesHomeScreenBottomAppBar = { NotesHomeScreenBottomAppBar(onEvent = {}) },
            onEvent = {},
            navController = navController
        )
    }
}