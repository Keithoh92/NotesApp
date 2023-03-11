package com.example.notesapp.ui.notesHomeScreen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.domain.NoteRepo
import com.example.notesapp.ui.notesHomeScreen.state.NotesListingItemState
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.notesHomeScreen.state.NotesHomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesHomeScreenViewModel @Inject constructor(
    val notesRepository: NoteRepo
): ViewModel() {

    var notesHomeScreenState by mutableStateOf(NotesHomeScreenState())
        private set

    var notesListingItemState by mutableStateOf(NotesListingItemState())
        private set

    fun onEvent(event: BaseComposeEvent) {
        when (event) {
            is NotesHomeScreenEvent.OnNoteClicked -> onClickSearch() // TODO must change this
            is NotesHomeScreenEvent.OnMinimiseNote -> onMinimiseNote(event.noteId, event.isMinimised ?: false)
            is NotesHomeScreenEvent.OnClickSearch -> onClickSearch()
            is NotesHomeScreenEvent.OnClickShare -> onClickShare()
            is NotesHomeScreenEvent.OnClickNotifications -> onClickNotifications()
            is NotesHomeScreenEvent.OnLongPressImage -> onClickImagePreview(event.noteTitle, event.image)
            is NotesHomeScreenEvent.OnReleaseLongPressImage -> onDismissImagePreview()
            is NotesHomeScreenEvent.OnLongPressNote -> onClickNotePreview(event.noteTitle, event.note)
            is NotesHomeScreenEvent.OnReleaseLongPressNote -> onDismissNotePreview()
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