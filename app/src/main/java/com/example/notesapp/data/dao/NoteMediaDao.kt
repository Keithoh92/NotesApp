package com.example.notesapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.data.entity.NoteMedia

@Dao
interface NoteMediaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(media: NoteMedia): Long

    @Query("SELECT * from note_media WHERE noteId = :noteId")
    fun getMedia(noteId: Int): List<NoteMedia>
}