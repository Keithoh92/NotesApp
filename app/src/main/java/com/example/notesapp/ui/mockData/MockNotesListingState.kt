package com.example.notesapp.ui.mockData

import com.example.notesapp.R
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.notesHomeScreen.state.NotesListingItemState
import java.util.*

data class NotesListingState(
    val notes: List<NoteInfo> = mockNoteInfo()
)

fun mockNoteInfo() : List<NoteInfo> {
    val noteInfoList = mutableListOf<NoteInfo>()

    val listOfImages = listOf(emptyList(), emptyList(), mockListOfImages(), mockListOfImages(), emptyList(), mockListOfImages())

    for (i in 0..5) {
        noteInfoList.add(
            NoteInfo(
                id = i,
                title = "Note $i",
                note = loremEpsum,
                category = "Personal",
                noteImages = emptyList(),
                voiceNotes = emptyList(),
                videoNotes = emptyList(),
                date = Date(1641052800000L),
                timestamp = Date(1641052800000L),
                cardViewSelectedId = 1
            )
        )
    }

    return noteInfoList
}

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

fun mockNotesListState(): NotesListingItemState {
    return NotesListingItemState(
        showNoteItemPreview = false,
        noteTitle = "Note 1",
        imageToShow = null,
        noteToShow = null,
        notesMinimised = mutableMapOf<Int, Boolean>().apply { put(-1, false) }
    )
}

const val loremEpsum: String = "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, " +
        "totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, " +
        "explicabo. Nemo enim ipsam voluptatem, quia voluptas sit, " +
        "aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque porro "
