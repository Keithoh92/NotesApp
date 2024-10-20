package com.example.notesapp.ui.noteScreen.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.R
import com.example.notesapp.common.logs.NLog
import com.example.notesapp.domain.StringResHelper
import com.example.notesapp.domain.database.NoteRepo
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.noteScreen.NoteScreenDialogDetails
import com.example.notesapp.ui.noteScreen.event.NoteScreenEvent
import com.example.notesapp.ui.noteScreen.state.NoteScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(
    private val notesRepository: NoteRepo,
    private val stringResHelper: StringResHelper
): ViewModel() {

    var noteScreenState by mutableStateOf(NoteScreenState())
        private set

    private val _uiState = MutableStateFlow(NoteScreenState())
    val uiState: StateFlow<NoteScreenState> = this._uiState.asStateFlow()

    private val _displayToast = MutableSharedFlow<Int>()
    val displayToast = _displayToast.asSharedFlow()

    private var currentNoteTitleState = uiState.value.currentTitle
    private var currentNoteState = uiState.value.currentNote

    fun fetchOpenedNote(noteId: Int) = viewModelScope.launch {
        if (noteId != "0") {
            val note = notesRepository.getNote(noteId = noteId.toInt())
            NLog.d("note = $note")
            _uiState.update {
                it.copy(
                    isNewNote = false,
                    existingNote = note,
                    currentTitle = note.title,
                    currentNote = note.note
                )
            }
        }
    }

    fun onEvent(event: BaseComposeEvent) {
        when (event) {
            is NoteScreenEvent.OnNoteChanged -> onNoteChanged(event.note)
            is NoteScreenEvent.OnTitleChanged -> onTitleChanged(event.noteTitle)
            is NoteScreenEvent.OnBackClicked -> onBackClicked()
            is NoteScreenEvent.OnSaveClicked -> onSaveClicked()
            is NoteScreenEvent.DialogSave -> saveUpdatedNote()
            is NoteScreenEvent.DialogDismiss ->
                _uiState.update { it.copy(saveChangesDialog = false) }
        }
    }

    private fun displayToastMessage(message: Int) = viewModelScope.launch {
        _displayToast.emit(message)
    }

    private fun onSaveClicked() {
        if (isNoteEmpty()) {
            handleInvalidNote()
            return
        }

        if (!uiState.value.isNewNote) {
            saveUpdatedNote()
            return
        }

        createNote()
        navigateHome()
    }

    private fun handleInvalidNote() {
        displayToastMessage(R.string.no_note_to_save_toast)
        NLog.d("No Note to save")
        navigateHome()
    }

    private fun isNoteEmpty(): Boolean = currentNoteTitleState.isEmpty() && currentNoteState.isEmpty()

    private fun isNoteValid(): Boolean = currentNoteState.isNotEmpty() || currentNoteTitleState.isNotEmpty()

    private fun onBackClicked() {
        if (isNoteValid()) {
            if (uiState.value.isNewNote) {
                createNote()
                navigateHome()
            } else {
                checkIfEditedExistingNote()
            }
        } else {
            navigateHome()
        }
    }

    private fun navigateHome() {
        _uiState.update {
            it.copy(shouldReturnToHome = true)
        }
    }

    private fun onTitleChanged(title: String) {
        currentNoteTitleState = title
        _uiState.update {
            it.copy(currentTitle = title)
        }
    }

    private fun onNoteChanged(note: String) {
        currentNoteState = note
        _uiState.update {
            it.copy(currentNote = note)
        }
    }

    private fun createNote() = viewModelScope.launch {
        NLog.d("CreatingNote")
        NLog.d("title = $currentNoteTitleState, note = $currentNoteState")
        val newNote = NoteInfo(
            id = 0,
            title = currentNoteTitleState,
            note = currentNoteState,
            noteImages = null,
            videoNotes = null,
            voiceNotes = null,
            timestamp = Date(),
            category = null,
            cardViewSelectedId = 1
        )
        NLog.d("Inserting note into DB")
        notesRepository.insert(newNote)
    }

    private fun checkIfEditedExistingNote(title: String? = "", note: String? = "") {
        val existingNote = uiState.value.existingNote
        if (existingNote.title != title || existingNote.note != note) {
            val noteScreenDialogDetails = NoteScreenDialogDetails(
                title = stringResHelper.getString(R.string.note_changed),
                message = stringResHelper.getString(R.string.note_changed_message)
            )
            _uiState.update {
                it.copy(
                    saveChangesDialog = true,
                    noteScreenDialogDetails = noteScreenDialogDetails
                )
            }
        }
    }

    private fun saveUpdatedNote() = viewModelScope.launch {
        val existingNote = uiState.value.existingNote
        val currentNoteState = uiState.value.currentNote
        val currentNoteTitleState = uiState.value.currentTitle

        val updatedNote = NoteInfo(
            id = existingNote.id,
            title = currentNoteTitleState.ifEmpty { "" },
            note = currentNoteState.ifEmpty { "" },
            noteImages = null,
            videoNotes = null,
            voiceNotes = null,
            timestamp = DateTime.now().toDate(),
            category = existingNote.category,
            cardViewSelectedId = existingNote.id
        )

        notesRepository.update(updatedNote)
        navigateHome()
    }

//    private fun onConfirmDialogDiscardChangesToNote() {
//        TODO("Not yet implemented")
//    }
}