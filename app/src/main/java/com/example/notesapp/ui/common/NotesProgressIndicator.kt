package com.example.notesapp.ui.common

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.ui.theme.full
import com.example.notesapp.ui.theme.pointOne
import com.example.notesapp.ui.theme.size36
import com.example.notesapp.ui.theme.spacing4
import com.example.notesapp.ui.theme.width1

@Composable
fun NotesProgressIndicator(indicatorSize: Dp) {
    val infiniteTransition = rememberInfiniteTransition()

    val angle by infiniteTransition
        .animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 600
                }
            )
        )

    CircularProgressIndicator(
        progress = full,
        modifier = Modifier
            .size(indicatorSize)
            .rotate(angle)
            .border(
                spacing4,
                brush = Brush.sweepGradient(
                    listOf(
                        MaterialTheme.colors.surface,
                        MaterialTheme.colors.primary.copy(alpha = pointOne),
                        MaterialTheme.colors.primary
                    )
                ),
                shape = CircleShape
            ),
        strokeWidth = width1,
        color = MaterialTheme.colors.surface
    )
}

@Preview("Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NotesProgressIndicatorPreview() {
    NotesAppTheme {
        Surface {
            NotesProgressIndicator(indicatorSize = size36)
        }
    }
}