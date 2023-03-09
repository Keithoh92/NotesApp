package com.example.notesapp

import com.example.notesapp.data.entity.Note
import com.example.notesapp.feature.note.NoteInfo

fun Note.toNoteInfo(
    noteImagePath: List<String>? = emptyList(),
    noteVoicePath: List<String>? = emptyList(),
    noteVideoPath: List<String>? = emptyList()
): NoteInfo {
    return NoteInfo(
        id = this.id,
        title = this.title,
        note = this.note,
        category = this.category,
        noteImages = noteImagePath,
        voiceNotes = noteVoicePath,
        videoNotes = noteVideoPath,
        date = this.date,
        timestamp = this.timestamp,
        cardViewSelectedId = this.cardViewSelectedId
    )
}

fun NoteInfo.toNote(): Note {
    return Note(
        id = this.id,
        title = this.title,
        note = this.note,
        category = this.category,
        date = this.date,
        timestamp = this.timestamp,
        cardViewSelectedId = this.cardViewSelectedId
    )
}