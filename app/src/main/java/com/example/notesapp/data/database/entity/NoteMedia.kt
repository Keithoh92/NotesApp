package com.example.notesapp.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "note_media", foreignKeys = [
        ForeignKey(
            entity = Note::class,
            parentColumns = ["id"],
            childColumns = ["noteId"],
            onDelete = CASCADE
        )
    ],
    indices = [Index("noteId")]
)
data class NoteMedia(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val path: String,
    val mediaType: Int,
    val noteId: Int
)
