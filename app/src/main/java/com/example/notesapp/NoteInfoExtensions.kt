package com.example.notesapp

import com.example.notesapp.data.NoteInfoDao
import com.example.notesapp.ui.notes.data.NoteInfo

fun NoteInfoDao.toNoteInfo(): NoteInfo {
    return NoteInfo(
        id = this.id,
        title = this.title,
        note = this.note,
        category = this.category,
        noteImages = this.noteImages,
        voiceNotes = this.voiceNotes,
        videoNotes = this.videoNotes,
        date = this.date,
        timestamp = this.timestamp,
        cardViewSelectedId = this.cardViewSelectedId
    )
}