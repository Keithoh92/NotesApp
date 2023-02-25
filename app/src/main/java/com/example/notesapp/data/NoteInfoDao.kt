package com.example.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user_notes")
data class NoteInfoDao(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val note: String,
    val category: String?,
    val noteImages: List<Int>?,
    val voiceNotes: List<String>?,
    val videoNotes: List<String>?,
    val date: Date,
    val timestamp: Date,
    val cardViewSelectedId: Int
)