package com.example.notesapp.ui.notes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.domain.NoteRepo
import com.example.notesapp.ui.notes.data.NotesListingItemState
import com.example.notesapp.ui.notes.data.NotesListingState
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.event.NotesAppEvent
import com.example.notesapp.ui.notes.data.NotesScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesScreenViewModel @Inject constructor(
    val notesRepository: NoteRepo
): ViewModel() {

    var notesScreenState by mutableStateOf(NotesScreenState())
        private set

    var notesListingItemState by mutableStateOf(NotesListingItemState())
        private set

    fun onEvent(event: BaseComposeEvent) {
        when (event) {
            is NotesAppEvent.OnNoteClicked -> onClickSearch() // TODO must change this
            is NotesAppEvent.OnMinimiseNote -> onMinimiseNote(event.noteId, event.isMinimised ?: false)
            is NotesAppEvent.OnClickSearch -> onClickSearch()
            is NotesAppEvent.OnClickShare -> onClickShare()
            is NotesAppEvent.OnClickNotifications -> onClickNotifications()
            is NotesAppEvent.OnLongPressImage -> onClickImagePreview(event.noteTitle, event.image)
            is NotesAppEvent.OnReleaseLongPressImage -> onDismissImagePreview()
            is NotesAppEvent.OnLongPressNote -> onClickNotePreview(event.noteTitle, event.note)
            is NotesAppEvent.OnReleaseLongPressNote -> onDismissNotePreview()
        }
    }

    private fun onMinimiseNote(noteId: Int, isMinimised: Boolean) {
        val updatedMap = notesListingItemState.notesMinimised.toMutableMap()
        updatedMap[noteId] = !isMinimised

        notesListingItemState = notesListingItemState.copy(
            notesMinimised = updatedMap
        )
    }

    private fun onClickSearch() = viewModelScope.launch {

    }

    private fun onClickShare() = viewModelScope.launch {

    }

    private fun onClickNotifications() = viewModelScope.launch {

    }

    private fun onClickImagePreview(noteTitle: String, image: String) {
        notesListingItemState = notesListingItemState.copy(
            noteTitle = noteTitle,
            showNoteItemPreview = true,
            imageToShow = image
        )
    }

    private fun onDismissImagePreview() {
        notesListingItemState = notesListingItemState.copy(
            showNoteItemPreview = false
        )
    }

    private fun onClickNotePreview(noteTitle: String, note: String) {
        notesListingItemState = notesListingItemState.copy(
            noteTitle = noteTitle,
            showNoteItemPreview = true,
            imageToShow = null,
            noteToShow = note
        )
    }

    private fun onDismissNotePreview() {
        notesListingItemState = notesListingItemState.copy(
            showNoteItemPreview = false
        )
    }

}