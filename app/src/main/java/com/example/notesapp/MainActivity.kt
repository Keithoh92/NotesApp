package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.ui.HomeScreenMain
import dagger.hilt.android.AndroidEntryPoint
import java.io.OutputStream

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HomeScreenMain(this::saveImageToGallery)
        }
    }

    private fun saveImageToGallery(imageData: Pair<Bitmap, String>): Boolean {
        val displayName = "${imageData.second}.jpg"
        val bitmap = imageData.first

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.WIDTH, bitmap.width)
            put(MediaStore.Images.Media.HEIGHT, bitmap.height)
        }

        val uri = this.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            ?: return false

        return try {
            val outputStream: OutputStream = this.contentResolver.openOutputStream(uri)
                ?: return false
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.close()

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    HomeScreenMain(saveBitmap = {})
}