package com.example.notesapp.ui.notesHomeScreen.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImagePainter
import com.example.notesapp.R
import com.example.notesapp.common.logs.NLog
import com.example.notesapp.domain.StringResHelper
import com.example.notesapp.domain.database.NoteRepo
import com.example.notesapp.feature.note.NoteInfo
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.noteScreen.NoteScreenDialogDetails
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.notesHomeScreen.state.NotesHomeScreenState
import com.example.notesapp.ui.notesHomeScreen.state.NotesListingItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.joda.time.DateTime
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class NotesHomeScreenViewModel @Inject constructor(
    private val notesRepository: NoteRepo,
    private val stringResHelper: StringResHelper
) : ViewModel() {

    var notesHomeScreenState by mutableStateOf(NotesHomeScreenState())
        private set

    private val _uiState = MutableStateFlow(NotesHomeScreenState())
    val uiState: StateFlow<NotesHomeScreenState> = this._uiState.asStateFlow()

    private val _listingItemUIState = MutableStateFlow(NotesListingItemState())
    val listingItemUIState: StateFlow<NotesListingItemState> = this._listingItemUIState.asStateFlow()

    var notesListingItemState by mutableStateOf(NotesListingItemState())
        private set

    private val _saveBitmap = MutableSharedFlow<Pair<Bitmap, String>>()
    val saveBitmap = _saveBitmap.asSharedFlow()

//    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

    fun onEvent(event: BaseComposeEvent) {
        when (event) {
            is NotesHomeScreenEvent.OnNoteClicked -> onNoteClicked(event.noteId) // TODO must change this
            is NotesHomeScreenEvent.OnNoteOpened -> _uiState.update { it.copy(noteClicked = -1 to false) }
            is NotesHomeScreenEvent.OnMinimiseNote -> onMinimiseNote(event.noteId, event.isMinimised ?: false)
            is NotesHomeScreenEvent.OnNoteSwipeExpanded -> onNoteSwipeExpanded(event.noteId)
            is NotesHomeScreenEvent.OnNoteSwipeCollapsed -> onNoteSwipeCollapsed(event.noteId)
            is NotesHomeScreenEvent.OnClickSearch -> onClickSearch()
            is NotesHomeScreenEvent.OnClickShare -> onClickShare()
            is NotesHomeScreenEvent.OnClickNotifications -> onClickNotifications()
            is NotesHomeScreenEvent.OnClickNoteDelete -> onClickNoteDelete(event.noteId)
            is NotesHomeScreenEvent.OnConfirmDialogDeleteNote -> deleteNote(event.noteId)
            is NotesHomeScreenEvent.OnCancelDialogDeleteNote -> dismissDeleteNoteDialog()
            is NotesHomeScreenEvent.OnLongPressImage -> onClickImagePreview(event.noteTitle, event.painter)
            is NotesHomeScreenEvent.OnReleaseLongPressImage -> onDismissImagePreview()
            is NotesHomeScreenEvent.OnLongPressNote -> onClickNotePreview(event.noteTitle, event.note)
            is NotesHomeScreenEvent.OnReleaseLongPressNote -> onDismissNotePreview()
        }
    }

    init {
        fetchAllNotes()
    }

    private fun fetchAllNotes() = viewModelScope.launch {
        val allNotes = mutableListOf<NoteInfo>()
        val notes = notesRepository.getAllNotes()
        NLog.d("notes in DB = $notes")

        allNotes.addAll(notes)

        when (allNotes.size) {
            0 -> {
//                downloadWelcomeNoteImages()
                allNotes.addAll(notesRepository.getAllNotes())
            }
            1 -> Unit
            else -> {
                removeWelcomeNoteIfExists(allNotes)
                allNotes.clear()
                allNotes.addAll(notesRepository.getAllNotes())
            }
        }

        _uiState.update { it.copy(notes = allNotes) }
    }

    private fun enableLoadPlaceHolderImages() {
        _uiState.update { it.copy(shouldLoadPlaceHolderImages = true) }
    }

    private fun dismissLoadingDialog() { _uiState.update { it.copy(isLoading = false) } }

    private fun showLoadingDialog() { _uiState.update { it.copy(isLoading = true) } }

    private fun removeWelcomeNoteIfExists(notes: List<NoteInfo>) = runBlocking {
        _uiState.update { it.copy(shouldLoadPlaceHolderImages = false) }
        if (notes.any { it.title == "Welcome!"}) notesRepository.deleteWelcomeNote()
    }

    private suspend fun insertWelcomeDisplayNote(imageUrls: List<String>) {
        val note = NoteInfo(
            id = 0,
            title = "Welcome!",
            note = "Click and hold an image to preview",
            category = null,
            noteImages = imageUrls,
            voiceNotes = null,
            videoNotes = null,
            timestamp = DateTime.now().toDate(),
            cardViewSelectedId = 2
        )
        notesRepository.insert(note)
        _uiState.update { it. copy(notes = listOf(note)) }
        dismissLoadingDialog()
    }

    private fun saveImages(imageUrls: List<String>) {
        val bitmaps = mutableListOf<Bitmap>()

        for (i in 0..imageUrls.size) {
            val bitmap = downloadImage(imageUrls[i])
            if (bitmap != null) {
                bitmaps.add(bitmap)
            }
        }

//        saveImagesToDevice(bitmaps)
    }

//    private fun saveImagesToDevice(bitmaps: List<Bitmap>) {
//        _saveBitmap.emit(Pair<>)
//    }

//    private fun saveImageToGallery(context: Context, bitmap: Bitmap, displayName: String): Boolean {
//        val contentValues = ContentValues().apply {
//            put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
//            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
//            put(MediaStore.Images.Media.WIDTH, bitmap.width)
//            put(MediaStore.Images.Media.HEIGHT, bitmap.height)
//        }
//
//        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//            ?: return false
//
//        return try {
//            val outputStream: OutputStream = context.contentResolver.openOutputStream(uri)
//                ?: return false
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//            outputStream.close()
//
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }

    private fun downloadImage(imageUrl: String): Bitmap? {
        return try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()

            val inputStream: InputStream = connection.inputStream
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun onMinimiseNote(noteId: Int, isMinimised: Boolean) {
        val updatedMap = listingItemUIState.value.notesMinimised.toMutableMap()
        updatedMap[noteId] = !isMinimised

        _listingItemUIState.update { it.copy(notesMinimised = updatedMap) }
    }

    private fun onNoteSwipeCollapsed(noteId: Int) {
        val updatedSwipeExpandedNoteCardMap = uiState.value.revealedCardIds.toMutableMap()
        updatedSwipeExpandedNoteCardMap[noteId] = false

        _uiState.update { it.copy(revealedCardIds = updatedSwipeExpandedNoteCardMap) }
    }

    private fun onNoteSwipeExpanded(noteId: Int) {
        val updatedSwipeExpandedNoteCardMap = uiState.value.revealedCardIds.toMutableMap()
        updatedSwipeExpandedNoteCardMap[noteId] = true

        _uiState.update { it.copy(revealedCardIds = updatedSwipeExpandedNoteCardMap) }
    }

    private fun onNoteClicked(noteId: Int) {
        _uiState.update { it.copy(noteClicked = noteId to true) }
    }

    private fun onClickSearch() = viewModelScope.launch {

    }

    private fun onClickShare() = viewModelScope.launch {

    }

    private fun onClickNotifications() = viewModelScope.launch {

    }

    private fun onClickNoteDelete(noteId: Int) {
        val noteScreenDialogDetails = NoteScreenDialogDetails(
            title = stringResHelper.getString(R.string.note_delete),
            message = stringResHelper.getString(R.string.delete_note_alert_message),
            noteId = noteId
        )

        _uiState.update { it.copy(
            deleteNoteDialog = true,
            noteScreenDialogDetails = noteScreenDialogDetails
        ) }
    }

    private fun deleteNote(noteId: Int) = viewModelScope.launch {
        notesRepository.delete(noteId)
        fetchAllNotes()
    }

    private fun dismissDeleteNoteDialog() {
        _uiState.update { it.copy(deleteNoteDialog = false) }
    }

    private fun onClickImagePreview(noteTitle: String, painter: AsyncImagePainter) {
        _listingItemUIState.update { it.copy(
            noteTitle = noteTitle,
            showNoteItemPreview = true,
            imageToShow = painter
        )}
    }

    private fun onDismissImagePreview() {
        _listingItemUIState.update { it.copy(
            showNoteItemPreview = false
        )}
    }

    private fun onClickNotePreview(noteTitle: String, note: String) {
        _listingItemUIState.update { it.copy(
            noteTitle = noteTitle,
            showNoteItemPreview = true,
            imageToShow = null,
            noteToShow = note
        )}
    }

    private fun onDismissNotePreview() {
        _listingItemUIState.update { it.copy(
            showNoteItemPreview = false
        )}
    }

}