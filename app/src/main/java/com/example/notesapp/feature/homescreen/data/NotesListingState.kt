package com.example.notesapp.feature.homescreen.data

import org.joda.time.DateTime

data class NotesListingState(
    val notes: List<NoteInfo> = mockNoteInfo()
)

fun mockNoteInfo() : List<NoteInfo> {
    val noteInfoList = mutableListOf<NoteInfo>()

    for (i in 0..5) {
        noteInfoList.add(
            NoteInfo(
                id = i,
                title = "Note $i",
                note = loremEpsum,
                noteImages = emptyList(),
                voiceNotes = emptyList(),
                videoNotes = emptyList(),
                date = DateTime.now().toDate(),
                timestamp = DateTime.now().toDate()
            )
        )
    }

    return noteInfoList
}

const val loremEpsum: String = "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, " +
        "totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, " +
        "explicabo. Nemo enim ipsam voluptatem, quia voluptas sit, " +
        "aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque porro "
