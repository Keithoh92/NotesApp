package com.example.notesapp.di

import android.content.Context
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
    fun provideApplicationContext(): Context {
        return NotesApplication.appContext
    }

    @Provides
    @Singleton
    fun provideDatabase(): NotesDatabase {
        return Room.databaseBuilder(NotesApplication.appContext, NotesDatabase::class.java, "NOTESDB")
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