package com.example.notesapp.ui.event

sealed class NotesAppEvent : BaseComposeEvent {
    data class OnNoteClicked(val noteId: Int) : NotesAppEvent()

    data class OnMinimiseNote(val noteId: Int, val isMinimised: Boolean?) : NotesAppEvent()

    object OnClickSearch : NotesAppEvent()

    object OnClickShare : NotesAppEvent()

    object OnClickNotifications : NotesAppEvent()

    data class OnLongPressImage(val noteTitle: String, val image: Int) : NotesAppEvent()

    object OnReleaseLongPressImage : NotesAppEvent()

    data class OnLongPressNote(val noteTitle: String, val note: String) : NotesAppEvent()

    object OnReleaseLongPressNote : NotesAppEvent()
}