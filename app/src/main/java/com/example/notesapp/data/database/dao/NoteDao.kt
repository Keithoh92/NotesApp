package com.example.notesapp.data.database.dao

import androidx.room.*
import com.example.notesapp.data.database.entity.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM user_notes ORDER BY timestamp DESC")
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM user_notes WHERE id = :noteId")
    fun getNote(noteId: Int): Note

    @Query("SELECT * FROM user_notes ORDER BY id DESC limit 1")
    fun getMostRecentNote(): Note

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(noteInfo: Note): Long

    @Update
    fun update(noteInfo: Note)

    @Query("DELETE FROM user_notes WHERE id = :noteId")
    fun delete(noteId: Int)

    @Query("DELETE FROM user_notes WHERE id = 1")
    fun deleteWelcomeNote()
}