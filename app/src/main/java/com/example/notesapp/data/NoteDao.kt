package com.example.notesapp.data

import androidx.room.*
import com.example.notesapp.ui.notes.data.NoteInfo

@Dao
interface NoteDao {

    @Query("SELECT * FROM user_notes ORDER BY timestamp DESC")
    fun getAllNotes(): List<NoteInfoDao>

    @Query("SELECT * FROM user_notes WHERE id = :noteId")
    fun getNote(noteId: Int): NoteInfoDao

    @Query("SELECT * FROM user_notes ORDER BY id DESC limit 1")
    fun getMostRecentNote(): NoteInfoDao

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(noteInfo: NoteInfo): Long

    @Update
    fun update(noteInfo: NoteInfo): Long

    @Query("DELETE FROM user_notes WHERE id = :noteId")
    fun delete(noteId: Int)
}