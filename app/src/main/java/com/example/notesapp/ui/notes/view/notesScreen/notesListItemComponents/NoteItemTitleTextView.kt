package com.example.notesapp.ui.notes.view.notesScreen.notesListItemComponents

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.notesapp.R

@Composable
fun NoteItemTitleTextView(
    title: String
) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        color = colorResource(id = R.color.orange_app)
    )
}