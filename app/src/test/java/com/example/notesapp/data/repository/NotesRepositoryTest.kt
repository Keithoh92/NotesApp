package com.example.notesapp.data.repository

import com.example.BaseTest
import com.example.notesapp.data.dao.NoteDao
import com.example.notesapp.data.dao.NoteMediaDao
import com.example.notesapp.data.entity.NoteMedia
import com.example.notesapp.domain.NoteRepo
import com.example.notesapp.domain.enum.MediaType
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.mocks.MockNoteData
import com.example.notesapp.toNote
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class NotesRepositoryTest : BaseTest() {

    private lateinit var noteRepo: NoteRepo

    @RelaxedMockK
    private lateinit var noteDao: NoteDao

    @RelaxedMockK
    private lateinit var noteMediaDao: NoteMediaDao

    private val noteInfo = NoteInfo(
        id = 1,
        note = "Test Note insert",
        title = "Testing Insert",
        category = null,
        date = Date(),
        timestamp = Date(),
        noteImages = listOf("pathToImage1", "pathToImage2"),
        voiceNotes = null,
        videoNotes = null,
        cardViewSelectedId = 1
    )

    private val updatedNoteInfo = NoteInfo(
        id = 1,
        note = "Test Note insert update",
        title = "Testing Insert update",
        category = null,
        date = Date(),
        timestamp = Date(),
        noteImages = listOf("pathToImage1", "pathToImage2"),
        voiceNotes = null,
        videoNotes = null,
        cardViewSelectedId = 2
    )

    private val noteMediaInfo = listOf(
        NoteMedia(
            id = 0,
            path = "pathToImage1",
            mediaType = MediaType.IMAGE.ordinal,
            noteId = 1
        ),
        NoteMedia(
            id = 1,
            path = "pathToImage2",
            mediaType = MediaType.IMAGE.ordinal,
            noteId = 1
        )
    )

    override fun setUp() {
        super.setUp()
        noteRepo = NotesRepository(noteDao, noteMediaDao)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getAllNotes - Returns a list of notes from the DB`() = runTest {
        every { noteDao.getAllNotes() } returns MockNoteData.mockNoteData()

        val result = noteRepo.getAllNotes()

        verify { noteDao.getAllNotes() }

        assertEquals(4, result.size)
        assertEquals(emptyList(), result.first().noteImages)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getAllNotes - Returns a note with image path fetched from NoteMedia table`() = runTest {
        every { noteDao.getAllNotes() } returns MockNoteData.mockNoteData()
        every { noteMediaDao.getMedia(1) } returns listOf(MockNoteData.mockNoteMediaData()[1])

        val result = noteRepo.getAllNotes()

        verify { noteMediaDao.getMedia(1) }

        assertEquals(4, result.size)
        assertEquals("pathToImage1", result[1].noteImages?.first())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getNote - returns a single noteInfo`() = runTest {
        every { noteDao.getNote(1) } returns MockNoteData.mockNoteData()[1]

        val result = noteRepo.getNote(1)

        verify { noteDao.getNote(1) }

        assertEquals(1, result.id)
        assertEquals("Note 1", result.title)
        assertEquals("Test note 1", result.note)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert - Insert note inserts the note to the DB and returns a long to confirm`() = runTest {
        every { noteDao.insert(any()) } returns 1L
        every { noteMediaDao.insert(any()) } returns 1L

        val result = noteRepo.insert(noteInfo)

        verify { noteDao.insert(any()) }
        verify { noteMediaDao.insert(any()) }

        assertEquals(1L, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert - Insert note and perform fetch to ensure correct note is inserted and retrieved`() = runTest {
        every { noteDao.insert(any()) } returns 1L
        every { noteDao.getNote(1) } returns noteInfo.toNote()
        every { noteMediaDao.insert(any()) } returns 1L
        every { noteMediaDao.getMedia(1) } returns noteMediaInfo

        val insertion = noteRepo.insert(noteInfo)

        verify { noteDao.insert(any()) }
        verify { noteMediaDao.insert(any()) }

        val fetchedNoteAfterInsert = noteRepo.getNote(1)

        verify { noteDao.getNote(1) }
        verify { noteMediaDao.getMedia(1) }

        assertEquals(1L, insertion)
        assertEquals("Test Note insert", fetchedNoteAfterInsert.note)
        assertEquals(2, fetchedNoteAfterInsert.noteImages?.size)
        assertEquals("pathToImage1", fetchedNoteAfterInsert.noteImages?.first())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `update - Updates an existing Note in DB with new data`() = runTest {
        every { noteDao.insert(any()) } returns 1L
        every { noteDao.getNote(1) } returns noteInfo.toNote()

        // insert
        val insertion = noteRepo.insert(noteInfo)
        verify { noteDao.insert(any()) }
        assertEquals(1L, insertion)

        // fetch
        val fetchedNoteAfterInsert = noteRepo.getNote(1)
        verify { noteDao.getNote(1) }
        assertEquals("Test Note insert", fetchedNoteAfterInsert.note)

        // update
        every { noteDao.update(any()) } returns Unit
        every { noteDao.getNote(1) } returns updatedNoteInfo.toNote()
        noteRepo.update(updatedNoteInfo)
        verify { noteDao.update(any()) }

        // fetch after update
        val fetchedNoteAfterUpdate = noteRepo.getNote(1)
        verify { noteDao.getNote(1) }
        assertEquals("Test Note insert update", fetchedNoteAfterUpdate.note)
    }
}