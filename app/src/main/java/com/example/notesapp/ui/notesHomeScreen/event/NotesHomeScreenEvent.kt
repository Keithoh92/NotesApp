package com.example.notesapp.ui.notesHomeScreen.event

import com.example.notesapp.ui.event.BaseComposeEvent

sealed class NotesHomeScreenEvent : BaseComposeEvent {
    data class OnNoteClicked(val noteId: Int) : NotesHomeScreenEvent()

    data class OnMinimiseNote(val noteId: Int, val isMinimised: Boolean?) : NotesHomeScreenEvent()

    object OnClickSearch : NotesHomeScreenEvent()

    object OnClickShare : NotesHomeScreenEvent()

    object OnClickNotifications : NotesHomeScreenEvent()

    data class OnLongPressImage(val noteTitle: String, val image: String) : NotesHomeScreenEvent()

    object OnReleaseLongPressImage : NotesHomeScreenEvent()

    data class OnLongPressNote(val noteTitle: String, val note: String) : NotesHomeScreenEvent()

    object OnReleaseLongPressNote : NotesHomeScreenEvent()
}