package com.example.notesapp.ui.notesHomeScreen.view.notesListItemComponents

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.notesapp.R
import com.example.notesapp.ui.event.BaseComposeEvent
import com.example.notesapp.ui.notesHomeScreen.event.NotesHomeScreenEvent
import com.example.notesapp.ui.theme.NotesAppTheme
import kotlin.math.roundToInt

const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

@SuppressLint("UnusedTransitionTargetStateParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotesListItemCardView(
    onClick: () -> Unit,
    noteId: Int,
    isRevealed: Boolean,
    cardOffset: Float,
    onEvent: (BaseComposeEvent) -> Unit,
    content: @Composable () -> Unit
) {

    val offsetX by remember { mutableStateOf(0f) }

    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }

    val transition = updateTransition(targetState = transitionState, label = "")
    val cardBgColor by transition.animateColor(
        label = "cardBgColorTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = {
            if (isRevealed) colorResource(id = R.color.light_grey_app) else colorResource(id = R.color.darker_grey_app)
        }
    )
    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) cardOffset - offsetX else -offsetX }
    )
    val cardElevation by transition.animateDp(
        label = "cardElevation",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) 40.dp else 2.dp }
    )

    Card(
        modifier = Modifier
            .offset { IntOffset((offsetX + offsetTransition).roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount >= MIN_DRAG_AMOUNT -> { onEvent(NotesHomeScreenEvent.OnNoteSwipeExpanded(noteId = noteId)) }
                        dragAmount < -MIN_DRAG_AMOUNT -> { onEvent(NotesHomeScreenEvent.OnNoteSwipeCollapsed(noteId = noteId)) }
                    }
                }
            }.fillMaxWidth(),
        onClick = { onClick() },
        elevation = cardElevation,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = cardBgColor,
        border = BorderStroke(
            width = 1.dp,
            color = Color.Black
        ),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun NotesListItemCardViewPreview() {
    NotesAppTheme {
        NotesListItemCardView(
            onClick = {},
            noteId = 1,
            isRevealed = false,
            cardOffset = 0f,
            onEvent = {},
            content = {}
        )
    }
}