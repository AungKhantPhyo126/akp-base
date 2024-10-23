package com.dev.designsystem.ui.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dev.designsystem.R
import com.dev.designsystem.ui.theme.AppTheme

@Composable
fun ShadowedImage(
    imageOpacity:Float = 0.8f,
    shadowColor:Color = Color.Black,
    shadowOffsetX:Dp = 0.dp,
    shadowOffsetY:Dp = 45.dp,
    shadowOpacity:Float = 0.9f,
    shadowShape:Shape = RectangleShape,
    imageWidth:Dp =120.dp,
    imageHeight:Dp =120.dp,
) {
    Box(
        modifier = Modifier.size(120.dp) // Set the size of the image
    ) {
        // Image with reduced opacity
        Image(
            painter = painterResource(id = R.drawable.akp),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(imageWidth)
                .height(imageHeight)
                .graphicsLayer {
                    alpha =
                        imageOpacity // Set image opacity here (0.0f fully transparent, 1.0f fully opaque)
                }
        )

        // Overlay shadow or color
        Box(
            modifier = Modifier
                .matchParentSize() // Covers the entire image
                .offset(x = shadowOffsetX, y = shadowOffsetY)
                .graphicsLayer {
                    alpha = shadowOpacity // You can adjust this for shadow intensity
                }
                .background(shadowColor, shadowShape) // Semi-transparent black overlay
        )
    }
}

@Composable
fun GradientShadowedBox(
    modifier: Modifier = Modifier,
    colorList:List<Color> = listOf(
        Color(0xFF020122).copy(alpha = 0.1f),
        Color(0xFF020122).copy(alpha = 1f),
    ),
    radius:Float = 0.5f,
    centerOffset: Offset,
    content: @Composable () -> Unit

) {
    Box (
        modifier = modifier.drawWithContent {
            drawContent()
            drawRect(
                brush = Brush.radialGradient(
                    colors = colorList,
                    center = centerOffset,
                    radius = radius
                ),
            )
        },
        content = { content() }
    )
}

@Preview(showBackground = true)
@Composable
private fun ShadowedImagePreview(modifier: Modifier = Modifier) {
    AppTheme {
        ShadowedImage(
            shadowOffsetX = 0.dp,
            shadowOffsetY = 0.dp
        )
    }

}