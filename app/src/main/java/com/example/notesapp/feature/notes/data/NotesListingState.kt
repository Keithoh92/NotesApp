package com.example.notesapp.feature.notes.data

import com.example.notesapp.R
import org.joda.time.DateTime

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
                noteImages = listOfImages[i],
                voiceNotes = emptyList(),
                videoNotes = emptyList(),
                date = DateTime.now().toDate(),
                timestamp = DateTime.now().toDate(),
                cardViewSelectedId = 1
            )
        )
    }

    return noteInfoList
}

fun mockListOfImages(): List<Int> {
    return listOf(
        R.drawable.image_02_12_2022_at_13_28,
        R.drawable.image_06_12_2022_at_11_25,
        R.drawable.image_06_12_2022_at_11_26,
        R.drawable.image_09_12_2022_at_17_12,
        R.drawable.image_13_12_2022_at_10_00,
        R.drawable.image_13_12_2022_at_10_03
    )
}

const val loremEpsum: String = "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, " +
        "totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, " +
        "explicabo. Nemo enim ipsam voluptatem, quia voluptas sit, " +
        "aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque porro "
