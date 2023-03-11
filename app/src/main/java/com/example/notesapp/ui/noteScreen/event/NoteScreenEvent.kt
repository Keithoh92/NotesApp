package com.example.notesapp.ui.noteScreen.event

import com.example.notesapp.ui.event.BaseComposeEvent

sealed class NoteScreenEvent : BaseComposeEvent {

    data class OnTitleChanged(val noteTitle: String): NoteScreenEvent()

    data class OnNoteChanged(val note: String): NoteScreenEvent()
}
