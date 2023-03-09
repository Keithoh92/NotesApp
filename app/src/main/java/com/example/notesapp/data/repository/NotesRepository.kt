package com.example.notesapp.data.repository

import com.example.notesapp.data.dao.NoteDao
import com.example.notesapp.data.dao.NoteMediaDao
import com.example.notesapp.data.entity.NoteMedia
import com.example.notesapp.domain.NoteRepo
import com.example.notesapp.domain.enum.MediaType
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.toNote
import com.example.notesapp.toNoteInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesRepository(
    private val noteDao: NoteDao,
    private val noteMediaDao: NoteMediaDao
    ) : NoteRepo {

    private val noteImagePaths = mutableListOf<String>()
    private val noteVoicePaths = mutableListOf<String>()
    private val noteVideoPaths = mutableListOf<String>()

    override suspend fun getAllNotes(): List<NoteInfo> {
        return noteDao.getAllNotes().map {
            getMediaForNote(it.id).forEach { noteMedia ->
                when (noteMedia.mediaType) {
                    MediaType.IMAGE.ordinal -> noteImagePaths.add(noteMedia.path)
                    MediaType.VOICE.ordinal -> noteVoicePaths.add(noteMedia.path)
                    else -> noteVideoPaths.add(noteMedia.path)
                }
            }
            it.toNoteInfo(noteImagePaths, noteVoicePaths, noteVideoPaths)
        }
    }

    private suspend fun getMediaForNote(noteId: Int): List<NoteMedia> = withContext(Dispatchers.IO) {
        return@withContext noteMediaDao.getMedia(noteId)
    }

    override suspend fun getNote(noteId: Int): NoteInfo  {
        val note = noteDao.getNote(noteId)
        getMediaForNote(note.id).forEach { noteMedia ->
            when (noteMedia.mediaType) {
                MediaType.IMAGE.ordinal -> noteImagePaths.add(noteMedia.path)
                MediaType.VOICE.ordinal -> noteVoicePaths.add(noteMedia.path)
                else -> noteVideoPaths.add(noteMedia.path)
            }
        }

        return note.toNoteInfo(noteImagePaths, noteVoicePaths, noteVideoPaths)
    }

    override suspend fun getMostRecentNote(): NoteInfo = noteDao.getMostRecentNote().toNoteInfo()

    override suspend fun insert(noteInfo: NoteInfo): Long {
        mutableListOf<String>().apply {
            noteInfo.noteImages?.let { this.addAll(it) }
            noteInfo.voiceNotes?.let { this.addAll(it) }
            noteInfo.videoNotes?.let { this.addAll(it) }
        }.map { path ->
            val noteMedia = NoteMedia(
                id = 0,
                noteId = noteInfo.id,
                path = path,
                mediaType = MediaType.IMAGE.ordinal
            )
            insertNoteMedia(noteMedia)
        }

        return noteDao.insert(noteInfo.toNote())
    }

    private fun insertNoteMedia(noteMedia: NoteMedia) = noteMediaDao.insert(noteMedia)

    override suspend fun update(noteInfo: NoteInfo) = noteDao.update(noteInfo.toNote())

    override suspend fun delete(noteId: Int) = noteDao.delete(noteId)
}