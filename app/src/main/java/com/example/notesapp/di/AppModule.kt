package com.example.notesapp.di

import androidx.room.Room
import com.example.notesapp.NotesApplication
import com.example.notesapp.data.NotesDatabase
import com.example.notesapp.data.dao.NoteDao
import com.example.notesapp.data.dao.NoteMediaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteApplication(): NotesApplication {
        return NotesApplication()
    }

    @Provides
    @Singleton
    fun provideDatabase(application: NotesApplication): NotesDatabase {
        return Room.databaseBuilder(application, NotesDatabase::class.java, "NOTESDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(database: NotesDatabase): NoteDao {
        return database.noteDao
    }

    @Provides
    @Singleton
    fun provideNoteMediaDao(database: NotesDatabase): NoteMediaDao {
        return database.noteMediaDao
    }
}