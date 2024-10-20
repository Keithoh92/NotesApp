package com.example.notesapp.ui.notesHomeScreen.event

import coil.compose.AsyncImagePainter
import com.example.notesapp.ui.event.BaseComposeEvent

sealed interface NotesHomeScreenEvent : BaseComposeEvent {
    data class OnNoteClicked(val noteId: Int) : NotesHomeScreenEvent

    object OnNoteOpened : NotesHomeScreenEvent

    data class OnMinimiseNote(val noteId: Int, val isMinimised: Boolean?) : NotesHomeScreenEvent

    data class OnNoteSwipeExpanded(val noteId: Int) : NotesHomeScreenEvent

    data class OnNoteSwipeCollapsed(val noteId: Int) : NotesHomeScreenEvent

    object OnClickSearch : NotesHomeScreenEvent

    object OnClickShare : NotesHomeScreenEvent

    object OnClickNotifications : NotesHomeScreenEvent

    data class OnClickNoteDelete(val noteId: Int) : NotesHomeScreenEvent

    data class OnLongPressImage(val noteTitle: String, val painter: AsyncImagePainter) : NotesHomeScreenEvent

    object OnReleaseLongPressImage : NotesHomeScreenEvent

    data class OnLongPressNote(val noteTitle: String, val note: String) : NotesHomeScreenEvent

    object OnReleaseLongPressNote : NotesHomeScreenEvent

    data class OnConfirmDialogDeleteNote(val noteId: Int) : NotesHomeScreenEvent

    object OnCancelDialogDeleteNote : NotesHomeScreenEvent
}