package com.dev.designsystem.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dev.designsystem.ui.theme.AppTheme

@Composable
fun StackedCard(
    modifier: Modifier = Modifier,
    width:Dp = 200.dp,
    height:Dp = 100.dp,
    customRotationZ:Float = -5f,
    offsetX:Dp = 8.dp,
    offsetY:Dp = (-16).dp,
    secondCardContent: @Composable ColumnScope.() -> Unit,
    thirdCardContent: @Composable ColumnScope.() -> Unit,
    firstCardContainerColor:Color = Color.Red,
    secondCardContainerColor:Color = Color.Transparent,
    thirdCardContainerColor:Color = Color.Transparent,
    content: @Composable ColumnScope.() -> Unit
) {
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Card (
           modifier = Modifier
               .width(width)
               .height(height)
               .padding(8.dp)
               .offset(x = offsetX*2, y = offsetY*2)
               .graphicsLayer {
                   rotationZ = customRotationZ * 2
               },
            colors = CardDefaults.cardColors(
                containerColor = thirdCardContainerColor
            ),
            content = thirdCardContent
        )
        Card (
            modifier = Modifier
                .width(width)
                .height(height)
                .padding(8.dp)
                .offset(x = offsetX, y = offsetY)
                .graphicsLayer {
                    rotationZ = customRotationZ
                },
            colors = CardDefaults.cardColors(
                containerColor = secondCardContainerColor
            ),
            content = secondCardContent
        )
        Card (
            modifier = Modifier
                .width(width)
                .height(height)
                .padding(8.dp)
               ,
            colors = CardDefaults.cardColors(
                containerColor = firstCardContainerColor
            ),
            content = content
        )

    }
}

@Composable
fun StackedCardsExample(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly) {
        StackedCard(
            secondCardContent = {},
            thirdCardContent = {},
            content = {}
        )
        StackedCard(
            customRotationZ = 0f,
            secondCardContent = {},
            thirdCardContent = {},
            content = {}
        )
        StackedCard(
            customRotationZ = 0f,
            offsetX = 0.dp,
            secondCardContent = {},
            thirdCardContent = {},
            content = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StackedCardPreview(modifier: Modifier = Modifier) {
    AppTheme {
        StackedCardsExample()
    }
}