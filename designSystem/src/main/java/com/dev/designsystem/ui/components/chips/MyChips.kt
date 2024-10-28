package com.dev.designsystem.ui.components.chips

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Simple Chip
@Composable
fun SimpleChip(text: String) {
    AssistChip(
        onClick = { /* Handle click */ },
        label = { Text(text) }
    )
}

// Elevated Chip
@Composable
fun ElevatedChip(text: String) {
    ElevatedAssistChip(
        onClick = { /* Handle click */ },
        label = { Text(text) },
        elevation = AssistChipDefaults.elevatedAssistChipElevation(elevation = 4.dp)
    )
}

// Outlined Chip
@Composable
fun OutlinedChip(text: String) {
    AssistChip(
        onClick = { /* Handle click */ },
        label = { Text(text) },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray),
        colors = AssistChipDefaults.assistChipColors(containerColor = Color.Transparent)
    )
}

// Chip with Icon
@Composable
fun IconChip(text: String) {
    AssistChip(
        onClick = { /* Handle click */ },
        label = { Text(text) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorite Icon",
                tint = Color.Red,
                modifier = Modifier.size(18.dp)
            )
        }
    )
}

// Custom Styled Chip
@Composable
fun CustomStyledChip(text: String) {
    AssistChip(
        onClick = { /* Handle click */ },
        label = { Text(text, color = Color.White) },
        colors = AssistChipDefaults.assistChipColors(containerColor = Color.Cyan),
        shape = RoundedCornerShape(12.dp)
    )
}

// Column to display all chip types
@Composable
private fun ChipExamples() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Simple Chip", modifier = Modifier.padding(bottom = 8.dp))
        SimpleChip("Simple Chip")

        Spacer(modifier = Modifier.height(8.dp))

        Text("Elevated Chip", modifier = Modifier.padding(bottom = 8.dp))
        ElevatedChip("Elevated Chip")

        Spacer(modifier = Modifier.height(8.dp))

        Text("Outlined Chip", modifier = Modifier.padding(bottom = 8.dp))
        OutlinedChip("Outlined Chip")

        Spacer(modifier = Modifier.height(8.dp))

        Text("Chip with Icon", modifier = Modifier.padding(bottom = 8.dp))
        IconChip("Chip with Icon")

        Spacer(modifier = Modifier.height(8.dp))

        Text("Custom Styled Chip", modifier = Modifier.padding(bottom = 8.dp))
        CustomStyledChip("Custom Styled Chip")
    }
}

// Preview Composable
@Preview(showBackground = true)
@Composable
private fun ChipExamplesPreview() {
    ChipExamples()
}
