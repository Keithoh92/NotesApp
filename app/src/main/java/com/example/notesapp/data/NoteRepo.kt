package com.example.notesapp.data

import com.example.notesapp.ui.notes.data.NoteInfo

interface NoteRepo {

    suspend fun getAllNotes(): List<NoteInfo>

    suspend fun getNote(noteId: Int): NoteInfo

    suspend fun getMostRecentNote(): NoteInfo

    suspend fun insert(noteInfo: NoteInfo): Long

    suspend fun update(noteInfo: NoteInfo): Long

    suspend fun delete(noteId: Int)
}