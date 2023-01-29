package com.example.notesapp.feature.notes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.feature.notes.data.NotesListingItemState
import com.example.notesapp.feature.notes.data.NotesListingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesScreenViewModel @Inject constructor(): ViewModel() {

    var notesListingState by mutableStateOf(NotesListingState())
        private set

    var notesListingItemState by mutableStateOf(NotesListingItemState())
        private set

    fun onClickOpenDrawer() {

    }

    fun onClickSearch() = viewModelScope.launch {

    }

    fun onClickShare() = viewModelScope.launch {

    }

    fun onClickNotifications() = viewModelScope.launch {

    }

    fun onClickImagePreview(noteTitle: String, image: Int) {
        notesListingItemState = notesListingItemState.copy(
            noteTitle = noteTitle,
            showNoteItemPreview = true,
            imageToShow = image
        )
    }

    fun onDismissImagePreview() {
        notesListingItemState = notesListingItemState.copy(
            showNoteItemPreview = false
        )
    }

    fun onClickNotePreview(noteTitle: String, note: String) {
        notesListingItemState = notesListingItemState.copy(
            noteTitle = noteTitle,
            showNoteItemPreview = true,
            imageToShow = null,
            noteToShow = note
        )
    }

    fun onDismissNotePreview() {
        notesListingItemState = notesListingItemState.copy(
            showNoteItemPreview = false
        )
    }

}