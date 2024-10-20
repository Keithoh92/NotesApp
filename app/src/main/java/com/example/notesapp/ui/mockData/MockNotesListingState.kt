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
                timestamp = Date(1641052800000L),
                cardViewSelectedId = 1
            )
        )
    }

    return noteInfoList
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
