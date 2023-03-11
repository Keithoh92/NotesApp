package com.example.notesapp.ui.mockData

import com.example.notesapp.feature.note.NoteInfo
import org.joda.time.DateTime

fun mockNotesList(): List<NoteInfo> {

    val titles = listOf("Note 1", "Footy", "Trips", "Summer", "School", "Saturday")

    val note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
            "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit " +
            "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
            "Excepteur sint occaecat cupidatat non proident, sunt " +
            "in culpa qui officia deserunt mollit anim id est laborum."

    val categories = listOf("Personal", "Work", "Fun", "Misc")

    val cardViewSelectedIds = listOf(1, 3, 4, 2, 5, 4)

    val notesInfoList = mutableListOf<NoteInfo>()
    for (i in 0..5) {
        notesInfoList.add(
            NoteInfo(
                id = i,
                title = titles[i],
                note = note,
                category = null,
                noteImages = null,
                voiceNotes = null,
                videoNotes = null,
                date = DateTime.now().toDate(),
                timestamp = DateTime.now().toDate(),
                cardViewSelectedId = cardViewSelectedIds[i]
            )
        )
    }

    return notesInfoList
}