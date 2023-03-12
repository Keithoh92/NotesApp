package com.example.notesapp.ui.mockData

import com.example.notesapp.R
import com.example.notesapp.feature.note.NoteInfo
import org.joda.time.DateTime
import java.util.*

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

data class MockNoteInfo(
    val id: Int,
    val title: String,
    val note: String,
    val category: String?,
    val noteImages: List<Int>?,
    val voiceNotes: List<Int>?,
    val videoNotes: List<Int>?,
    val date: Date,
    val timestamp: Date,
    val cardViewSelectedId: Int
)

fun mockNoteForDisplayPurposes(): NoteInfo {
    return NoteInfo(
        id = 50,
        title = "Display Note",
        note = "CLick and hold an image to preview",
        category = null,
        noteImages = null,
        voiceNotes = null,
        videoNotes = null,
        date = DateTime.now().toDate(),
        timestamp = DateTime.now().toDate(),
        cardViewSelectedId = 1
    )
}

//
//fun NoteInfo.toMockNoteInfo(): MockNoteInfo {
//    return MockNoteInfo(
//        id = this.id,
//        title = this.title,
//        note = this.note,
//        category = this.category,
//        noteImages = this.,
//        voiceNotes = noteVoicePath,
//        videoNotes = noteVideoPath,
//        date = this.date,
//        timestamp = this.timestamp,
//        cardViewSelectedId = this.cardViewSelectedId
//    )
//}

fun mockListOfImages(): List<Int> {
    return listOf(
        R.drawable.images_2,
        R.drawable.images_3,
        R.drawable.images_4,
        R.drawable.istockphoto_1212174159_612x612,
        R.drawable.photo_1533450718592_29d45635f0a9,
        R.drawable.photo_1606115915090_be18fea23ec7,
        R.drawable.woman_girl_freedom_happy_39853,
        R.drawable.beach_resort_sunset_hd_wallpaper_background_jpg,
        R.drawable._958474
    )
}