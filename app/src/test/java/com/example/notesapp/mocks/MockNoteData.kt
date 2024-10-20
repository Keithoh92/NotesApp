package com.example.notesapp.mocks

import com.example.notesapp.data.database.entity.Note
import com.example.notesapp.data.database.entity.NoteMedia
import com.example.notesapp.domain.enum.MediaType
import java.util.Date

object MockNoteData {

    fun mockNoteData(): List<Note> {
        val noteList = mutableListOf<Note>()
        for (i in 0..3) {
            noteList.add(
                Note(
                    id = i,
                    title = "Note $i",
                    note = "Test note $i",
                    category = null,
                    timestamp = Date(),
                    cardViewSelectedId = i
                )
            )
        }
        return noteList
    }

    fun mockNoteMediaData(): List<NoteMedia> {
        val noteMediaList = mutableListOf<NoteMedia>()

        val mediaTypes = listOf(MediaType.IMAGE.ordinal, MediaType.VIDEO.ordinal, MediaType.VOICE.ordinal, MediaType.IMAGE.ordinal)
        for (i in 0..3) {
            noteMediaList.add(
                NoteMedia(
                    id = i,
                    path = "pathToImage$i",
                    mediaType = mediaTypes[0],
                    noteId = i
                )
            )
        }

        return noteMediaList
    }
}