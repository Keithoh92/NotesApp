package com.example.notesapp.ui.notesHomeScreen.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.domain.NoteRepo
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.notesHomeScreen.state.NotesListingItemState
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.mockData.mockListOfImages
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.notesHomeScreen.state.NotesHomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class NotesHomeScreenViewModel @Inject constructor(
    private val notesRepository: NoteRepo,
    private val applicationContext: Context
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

    init {
        fetchAllNotes()
    }

    private fun fetchAllNotes() = viewModelScope.launch {
        val allNotes = mutableListOf<NoteInfo>()
        allNotes.addAll(notesRepository.getAllNotes())
        if (allNotes.isEmpty()) {
            insertFakeDisplayNote()
            allNotes.addAll(notesRepository.getAllNotes())
        }
        notesHomeScreenState = notesHomeScreenState.copy(notes = allNotes)
    }

    private suspend fun insertFakeDisplayNote() {
        val noteImageJpegs = convertDrawablesToJpegsAndSave()
        val note = NoteInfo(
            id = 50,
            title = "Welcome!",
            note = "Click and hold an image to preview",
            category = null,
            noteImages = noteImageJpegs,
            voiceNotes = null,
            videoNotes = null,
            date = DateTime.now().toDate(),
            timestamp = DateTime.now().toDate(),
            cardViewSelectedId = 2
        )
        notesRepository.insert(note)
        notesHomeScreenState = notesHomeScreenState.copy(notes = listOf(note))
    }

    private fun convertDrawablesToJpegsAndSave(): List<String> {
        val resources = applicationContext.resources
        val displayNoteImageDrawables = mockListOfImages()
        val displayNoteJpegs = mutableListOf<String>()
        displayNoteImageDrawables.forEach {
            val bitmap = BitmapFactory.decodeResource(resources, it)
            val filename = "bitmap_${it}.jpeg"
            val file = File(applicationContext.filesDir, filename)
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.close()
            displayNoteJpegs.add(file.toString())
        }

        return displayNoteJpegs
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