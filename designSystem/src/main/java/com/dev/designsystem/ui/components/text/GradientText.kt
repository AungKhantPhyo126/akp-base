package com.dev.designsystem.ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.dev.designsystem.ui.theme.AppTheme

@Composable
fun GradientText(
    text: String,
    gradientColors: List<Color>,
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            brush = Brush.linearGradient(colors = gradientColors),
            fontSize = fontSize,
            fontWeight = fontWeight
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun GradientTextPreview(){
    AppTheme {
        GradientText(
            text = "Aung Khant Phyo",
            gradientColors = listOf(Color(0xFFE91E63), Color(0xFF2196F3))
        )
    }
}