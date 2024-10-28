package com.dev.designsystem.ui.components.textFields

import android.graphics.Color.WHITE
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.designsystem.ui.theme.spacing

@Composable
fun OtpText(
    modifier: Modifier = Modifier,
    text: String,
    unFocusBorderColor: Color = Color.White,
    focusBorderColor: Color = MaterialTheme.colorScheme.primary,
    isError: Boolean? = null,
    direction: Direction = Direction.START,
    showCursor: Boolean = false
) {
    val backgroundColor = if (showCursor) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }
    val borderColor = if (isError == true) MaterialTheme.colorScheme.error
    else if (text.isEmpty() && showCursor) focusBorderColor else if (text.isNotEmpty() && !showCursor) unFocusBorderColor else unFocusBorderColor

    val animateColor = animateColorAsState(targetValue = borderColor, label = "AnimateColor")

    val transition = rememberInfiniteTransition(label = "Transition")
    val alpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Alpha"
    )

    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(4.dp),
                color = backgroundColor
            )
            .border(
                width = 1.2.dp, color = animateColor.value, shape = RoundedCornerShape(8.dp)
            )

    ) {
        AnimatedVisibility(
            visible = text.isNotEmpty(),
            modifier = Modifier.align(Alignment.Center),
            enter = if (Direction.START == direction) slideInFadeIn() else slideDownFadeIn(),
            exit = if (Direction.START == direction) slideOutFadeOut() else slideUpFadeOut()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 24.sp,
                    color = Color.White
                ),
            )
        }

        AnimatedVisibility(
            visible = showCursor,
            modifier = Modifier.align(Alignment.Center),
            enter = fadeIn(tween(25)),
            exit = fadeOut(tween(25))
        ) {
            Surface(
                modifier = Modifier
                    .width(1.dp)
                    .height(spacing.space24)
            ) {}
        }
    }
}

@Preview()
@Composable
fun OtpTextPreview() {
    OtpText(text = "2")
}

enum class Direction {
    TOP, START
}

fun slideInFadeIn() = slideInHorizontally(
    animationSpec = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium
    )
) + fadeIn()

fun slideOutFadeOut() = slideOutHorizontally(
    spring(
        dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium
    )
) + fadeOut()

fun slideDownFadeIn() = slideInVertically(
    spring(
        dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium
    )
) + fadeIn()

fun slideUpFadeOut() = slideOutVertically(
    spring(
        dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium
    )
) + fadeOut()