package com.dev.designsystem.ui.components.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

fun Modifier.shake(isShake: Boolean): Modifier = composed {

//    ////Log.i("shakeReach",isShake.toString())
    val shake = remember { Animatable(0F) }

    LaunchedEffect(key1 = isShake, block = {
        if (isShake) {
            shake.animateTo(0f, animationSpec = shakeKeyframes)
        }
    })
    offset {
        IntOffset(shake.value.roundToInt(), 0)
    }
}
internal val shakeKeyframes: AnimationSpec<Float> = keyframes {
    durationMillis = 500
    val easing = FastOutSlowInEasing

    for (i in 1..5) {
        val x = when (i % 3) {
            0 -> 10f
            1 -> -10f
            else -> 0f
        }
        x at durationMillis / 10 * i with easing
    }
}