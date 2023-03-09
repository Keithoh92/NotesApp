package com.example.notesapp.di

import com.example.notesapp.data.dao.NoteDao
import com.example.notesapp.data.dao.NoteMediaDao
import com.example.notesapp.data.repository.NotesRepository
import com.example.notesapp.domain.NoteRepo
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
}