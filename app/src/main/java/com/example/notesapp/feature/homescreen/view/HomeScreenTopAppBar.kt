package com.example.notesapp.feature.homescreen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.notesapp.R
import com.example.notesapp.ui.theme.spacing10

@Composable
fun TopAppBarHomeScreen(
    title: String = "",
    buttonIcon: Painter,
    onButtonClicked: () -> Unit
) {
    TopAppBar(
        backgroundColor = colorResource(id = R.color.orange_app)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing10)
        ) {
            IconButton(
                onClick = { onButtonClicked }
            ) {
                Icon(
                    painter = buttonIcon,
                    contentDescription = ""
                )   
            }
        }
    }
}