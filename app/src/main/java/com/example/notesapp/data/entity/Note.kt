package com.example.notesapp.data.entity

import androidx.room.ColumnInfo
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
    @ColumnInfo(name = "date")
    val date: Date,
    val timestamp: Date,
    val cardViewSelectedId: Int
)