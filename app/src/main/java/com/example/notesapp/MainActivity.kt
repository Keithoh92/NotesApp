package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.feature.notes.view.HomeScreenMain
import com.example.notesapp.feature.notes.viewmodel.NotesScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notesScreenViewModel: NotesScreenViewModel by viewModels()

        setContent {
            HomeScreenMain(
                onClickSearch = notesScreenViewModel::onClickSearch,
                onClickShare = notesScreenViewModel::onClickShare,
                onClickNotifications = notesScreenViewModel::onClickNotifications
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    HomeScreenMain(
        onClickSearch = {},
        onClickShare = {},
        onClickNotifications = {}
    )
}