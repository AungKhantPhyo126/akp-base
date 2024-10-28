package com.dev.designsystem.ui.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.dev.designsystem.R

@Composable
fun networkImagePainter(
    url: String?,
): Painter = rememberAsyncImagePainter(
    model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .crossfade(true)
        .apply {

        }
//        .error(R.drawable.ic_placeholder_default)
        .build(),
)
