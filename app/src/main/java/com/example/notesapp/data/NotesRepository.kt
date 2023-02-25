package com.example.notesapp.data

import com.example.notesapp.ui.notes.data.NoteInfo
import com.example.notesapp.toNoteInfo

class NotesRepository(private val noteDao: NoteDao) : NoteRepo {

    override suspend fun getAllNotes(): List<NoteInfo> {
        return noteDao.getAllNotes().map { it.toNoteInfo() }
    }

    override suspend fun getNote(noteId: Int): NoteInfo = noteDao.getNote(noteId).toNoteInfo()

    override suspend fun getMostRecentNote(): NoteInfo = noteDao.getMostRecentNote().toNoteInfo()

    override suspend fun insert(noteInfo: NoteInfo): Long = noteDao.insert(noteInfo)

    override suspend fun update(noteInfo: NoteInfo): Long = noteDao.update(noteInfo)

    override suspend fun delete(noteId: Int) = noteDao.delete(noteId)
}