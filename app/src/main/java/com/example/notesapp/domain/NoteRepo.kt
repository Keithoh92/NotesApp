package com.example.notesapp.domain

import com.example.notesapp.feature.note.NoteInfo

interface NoteRepo {

    suspend fun getAllNotes(): List<NoteInfo>

    suspend fun getNote(noteId: Int): NoteInfo

    suspend fun getMostRecentNote(): NoteInfo

    suspend fun insert(noteInfo: NoteInfo): Long

    suspend fun update(noteInfo: NoteInfo)

    suspend fun delete(noteId: Int)
}