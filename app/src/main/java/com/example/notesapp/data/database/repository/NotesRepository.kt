package com.example.notesapp.data.database.repository

import com.example.notesapp.common.logs.NLog
import com.example.notesapp.data.database.dao.NoteDao
import com.example.notesapp.data.database.dao.NoteMediaDao
import com.example.notesapp.data.database.entity.NoteMedia
import com.example.notesapp.domain.database.NoteRepo
import com.example.notesapp.domain.enum.MediaType
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.toNote
import com.example.notesapp.toNoteInfo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class NotesRepository(
    private val noteDao: NoteDao,
    private val noteMediaDao: NoteMediaDao
    ) : NoteRepo {

    private val noteImagePaths = mutableListOf<String>()
    private val noteVoicePaths = mutableListOf<String>()
    private val noteVideoPaths = mutableListOf<String>()

    override suspend fun getAllNotes(): List<NoteInfo> = withContext(IO) {
        return@withContext noteDao.getAllNotes().map {
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

    private suspend fun getMediaForNote(noteId: Int): List<NoteMedia> = withContext(IO) {
        return@withContext noteMediaDao.getMedia(noteId)
    }

    override suspend fun getNote(noteId: Int): NoteInfo = withContext(IO)  {
        val note = noteDao.getNote(noteId)
        getMediaForNote(note.id).forEach { noteMedia ->
            when (noteMedia.mediaType) {
                MediaType.IMAGE.ordinal -> noteImagePaths.add(noteMedia.path)
                MediaType.VOICE.ordinal -> noteVoicePaths.add(noteMedia.path)
                else -> noteVideoPaths.add(noteMedia.path)
            }
        }

        return@withContext note.toNoteInfo(noteImagePaths, noteVoicePaths, noteVideoPaths)
    }

    override suspend fun getMostRecentNote(): NoteInfo = noteDao.getMostRecentNote().toNoteInfo()

    override suspend fun insert(noteInfo: NoteInfo): Long = withContext(IO) {
        NLog.d("Note to insert = $noteInfo")
        val insertResult = noteDao.insert(noteInfo.toNote())

        mutableListOf<String>().apply {
            noteInfo.noteImages?.let { this.addAll(it) }
            noteInfo.voiceNotes?.let { this.addAll(it) }
            noteInfo.videoNotes?.let { this.addAll(it) }
        }.map { path ->
            val noteMedia = NoteMedia(
                id = 0,
                noteId = insertResult.toInt(),
                path = path,
                mediaType = MediaType.IMAGE.ordinal
            )
            insertNoteMedia(noteMedia)
        }

        return@withContext insertResult
    }

    private suspend fun insertNoteMedia(noteMedia: NoteMedia) = withContext(IO) { noteMediaDao.insert(noteMedia) }

    override suspend fun update(noteInfo: NoteInfo) = withContext(IO) { noteDao.update(noteInfo.toNote()) }

    override suspend fun delete(noteId: Int) = withContext(IO) { noteDao.delete(noteId) }

    override suspend fun deleteWelcomeNote() = withContext(IO) { noteDao.deleteWelcomeNote() }
}