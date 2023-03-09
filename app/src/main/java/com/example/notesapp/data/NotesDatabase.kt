package com.example.notesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notesapp.data.dao.NoteDao
import com.example.notesapp.data.dao.NoteMediaDao
import com.example.notesapp.data.entity.DateConverter
import com.example.notesapp.data.entity.Note
import com.example.notesapp.data.entity.NoteMedia

@Database(entities = [Note::class, NoteMedia::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NotesDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao
    abstract val noteMediaDao: NoteMediaDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}