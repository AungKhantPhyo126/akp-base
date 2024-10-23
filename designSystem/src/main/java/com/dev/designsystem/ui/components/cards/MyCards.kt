package com.dev.designsystem.ui.components.cards


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Simple Card
@Composable
fun SimpleCardExample() {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = "Simple Card",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

// Elevated Card
@Composable
fun ElevatedCardExample() {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Text(
            text = "Elevated Card",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

// Outlined Card
@Composable
fun OutlinedCardExample() {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Text(
            text = "Outlined Card",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

// Card with Icon
@Composable
fun IconCardExample() {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Icon",
                tint = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "Card with Icon")
        }
    }
}

// Custom Styled Card
@Composable
fun CustomStyledCardExample() {
    Card(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Cyan
        )
    ) {
        Text(
            text = "Custom Styled Card",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

// Column to display all card types
@Composable
fun CardExamples() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Simple Card", modifier = Modifier.padding(bottom = 8.dp))
        SimpleCardExample()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Elevated Card", modifier = Modifier.padding(bottom = 8.dp))
        ElevatedCardExample()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Outlined Card", modifier = Modifier.padding(bottom = 8.dp))
        OutlinedCardExample()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Card with Icon", modifier = Modifier.padding(bottom = 8.dp))
        IconCardExample()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Custom Styled Card", modifier = Modifier.padding(bottom = 8.dp))
        CustomStyledCardExample()
    }
}

// Preview Composable
@Preview(showBackground = true)
@Composable
fun CardExamplesPreview() {
    CardExamples()
}
