package com.example.notesapp.ui.notesHomeScreen.viewmodel

import com.example.BaseTest
import com.example.notesapp.domain.NoteRepo
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.notesHomeScreen.state.NotesListingItemState
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.assert
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class NotesScreenViewModelTest : BaseTest() {

    private val notesListingItemState = mockk<NotesListingItemState>(relaxed = true)

    private lateinit var target: NotesHomeScreenViewModel

    @RelaxedMockK
    private lateinit var noteRepo: NoteRepo

    override fun setUp() {
        super.setUp()

        target = NotesHomeScreenViewModel(noteRepo)
    }

    @Test
    fun `onMinimiseNote - sets note with noteId = 1 - state(notesListingItemState - notesMinimised) to true when it is called`() {
        val mockMap = mutableMapOf<Int, Boolean>()
        mockMap[0] = false
        mockMap[1] = false

        every { notesListingItemState.notesMinimised } returns mockMap

        val resBeforeUpdatingState = target.notesListingItemState.notesMinimised[1]

        assert(resBeforeUpdatingState != true)

        target.onEvent(NotesHomeScreenEvent.OnMinimiseNote(1, false))

        val resAfterUpdatingState = target.notesListingItemState.notesMinimised[1] ?: false
        assert(resAfterUpdatingState)
    }

    @Test
    fun `onClickImagePreview() - updates the notesListingItemStates - noteTitle - showNoteItemPreview and imageToShow`() {
        val beforeUpdateStateNoteTitle = target.notesListingItemState.noteTitle
        val beforeUpdateStateNotePreview = target.notesListingItemState.showNoteItemPreview
        val beforeUpdateStateImageToShow = target.notesListingItemState.imageToShow

        assertNotEquals("New Note", beforeUpdateStateNoteTitle)
        assert(!beforeUpdateStateNotePreview)
        assertNotEquals("pathToNote", beforeUpdateStateImageToShow)

        target.onEvent(NotesHomeScreenEvent.OnLongPressImage("New Note", "pathToNote"))

        val afterUpdateStateNoteTitle = target.notesListingItemState.noteTitle
        val afterUpdateStateNotePreview = target.notesListingItemState.showNoteItemPreview
        val afterUpdateStateImageToShow = target.notesListingItemState.imageToShow

        assertEquals("New Note", afterUpdateStateNoteTitle)
        assert(afterUpdateStateNotePreview)
        assertEquals("pathToNote", afterUpdateStateImageToShow)
    }

    @Test
    fun `onDismissImagePreview() - updates the notesListingItemState - showNoteItemPreview to false`() {
        // set state preview to true
        target.onEvent(NotesHomeScreenEvent.OnLongPressImage("New Note", "pathToNote"))

        // before
        val beforeUpdateStateNotePreview = target.notesListingItemState.showNoteItemPreview
        assert(beforeUpdateStateNotePreview)

        target.onEvent(NotesHomeScreenEvent.OnReleaseLongPressImage)

        // after
        val afterDismissStateNotePreview = target.notesListingItemState.showNoteItemPreview
        assert(!afterDismissStateNotePreview)
    }

    @Test
    fun `onClickNotePreview() - updates the notesListingItemStates - noteTitle - showNoteItemPreview and noteToShow`() {
        val beforeUpdateStateNoteToShow = target.notesListingItemState.noteToShow

        assertNotEquals("Test note", beforeUpdateStateNoteToShow)

        target.onEvent(NotesHomeScreenEvent.OnLongPressNote("New Note", "Test Note"))

        val afterUpdateStateNoteToShow = target.notesListingItemState.noteToShow

        assertEquals("Test Note", afterUpdateStateNoteToShow)
    }

    @Test
    fun `onDismissNotePreview() - updates the notesListingItemState - showNoteItemPreview to false`() {
        // set state preview to true
        target.onEvent(NotesHomeScreenEvent.OnLongPressNote("New Note", "Test Note"))

        // before
        val beforeUpdateStateNotePreview = target.notesListingItemState.showNoteItemPreview
        assert(beforeUpdateStateNotePreview)

        target.onEvent(NotesHomeScreenEvent.OnReleaseLongPressNote)

        // after
        val afterDismissStateNotePreview = target.notesListingItemState.showNoteItemPreview
        assert(!afterDismissStateNotePreview)
    }

    @Test
    fun getNotesListingState() {
    }

    @Test
    fun getNotesListingItemState() {
    }

    @Test
    fun onEvent() {
    }

    @Test
    fun onClickOpenDrawer() {
    }

    @Test
    fun onClickSearch() {
    }

    @Test
    fun onClickShare() {
    }

    @Test
    fun onClickNotifications() {
    }
}