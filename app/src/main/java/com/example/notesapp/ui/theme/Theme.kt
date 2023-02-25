package com.example.notesapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.notesapp.R

@Composable
fun NotesAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val darkColorPalette = darkColors(
        primary = colorResource(id = R.color.dark_purple_app),
        primaryVariant = colorResource(id = R.color.dark_grey_app),
        secondary = colorResource(id = R.color.light_grey_app)
    )

    val lightColorPalette = lightColors(
        primary = colorResource(id = R.color.dark_purple_app),
        primaryVariant = colorResource(id = R.color.dark_grey_app),
        secondary = colorResource(id = R.color.light_grey_app)

        /* Other default colors to override
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
        */
    )

    val colors = if (darkTheme) { darkColorPalette } else { lightColorPalette }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}