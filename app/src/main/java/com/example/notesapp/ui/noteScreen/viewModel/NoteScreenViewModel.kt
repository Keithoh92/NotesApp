package com.example.notesapp.ui.noteScreen.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.repository.NotesRepository
import com.example.notesapp.domain.NoteRepo
import com.example.notesapp.ui.noteScreen.state.NoteScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(
    notesRepository: NoteRepo
): ViewModel() {

    var noteScreenState by mutableStateOf(NoteScreenState())
        private set

}