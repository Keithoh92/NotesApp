package com.example.notesapp.feature.notes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.feature.notes.data.NotesListingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesScreenViewModel @Inject constructor(): ViewModel() {

    val notesListingState by mutableStateOf(NotesListingState())

    fun onClickOpenDrawer() {

    }

    fun onClickSearch() = viewModelScope.launch {

    }

    fun onClickShare() = viewModelScope.launch {

    }

    fun onClickNotifications() = viewModelScope.launch {

    }

}