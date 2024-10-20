package com.example.notesapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user_notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val note: String,
    val category: String?,
    val timestamp: Date,
    val cardViewSelectedId: Int
)