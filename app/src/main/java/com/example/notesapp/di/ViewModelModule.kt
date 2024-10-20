package com.example.notesapp.di

import android.content.Context
import com.example.notesapp.data.database.dao.NoteDao
import com.example.notesapp.data.database.dao.NoteMediaDao
import com.example.notesapp.data.database.repository.NotesRepository
import com.example.notesapp.domain.StringResHelper
import com.example.notesapp.domain.database.NoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideNotesRepository(noteDao: NoteDao, noteMediaDao: NoteMediaDao): NoteRepo {
        return NotesRepository(noteDao, noteMediaDao)
    }

    @Provides
    @ViewModelScoped
    fun provideStringResHelper(context: Context): StringResHelper {
        return StringResHelper(context)
    }
}