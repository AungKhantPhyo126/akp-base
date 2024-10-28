package com.dev.designsystem.ui.components.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.dev.designsystem.ui.theme.AppTheme

// Text Button
@Composable
fun TextButtonExample() {
    TextButton(
        onClick = { /* Handle click */ },
        modifier = Modifier.padding(8.dp)
    ) {
        Text("Text Button")
    }
}

// Contained Button
@Composable
fun ContainedButtonExample() {
    Button(
        onClick = { /* Handle click */ },
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.buttonColors(),
        elevation = ButtonDefaults.elevatedButtonElevation()
    ) {
        Text("Contained Button")
    }
}

// Outlined Button
@Composable
fun OutlinedButtonExample() {
    OutlinedButton(
        onClick = { /* Handle click */ },
        modifier = Modifier.padding(8.dp),
        border = ButtonDefaults.outlinedButtonBorder(enabled = true)
    ) {
        Text("Outlined Button")
    }
}

// Elevated Button
@Composable
fun ElevatedButtonExample() {
    ElevatedButton(
        onClick = { /* Handle click */ },
        modifier = Modifier.padding(8.dp),
        elevation = ButtonDefaults.elevatedButtonElevation()
    ) {
        Text("Elevated Button")
    }
}

// Icon Button
@Composable
fun MyIconButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    icon:ImageVector = Icons.Default.Favorite,
    onClick:()->Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.padding(8.dp)
    ) {
        Icon(
            modifier = iconModifier,
            imageVector = icon,
            contentDescription = "Favorite")
    }
}

// Filled Icon Button
@Composable
fun MyFilledIconButton() {
    Button(
        onClick = { /* Handle click */ },
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.buttonColors(),
        elevation = ButtonDefaults.elevatedButtonElevation()
    ) {
        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
    }
}

// Toggle Button
@Composable
fun ToggleButtonExample() {
    var isChecked by remember { mutableStateOf(false) }
    Button(
        onClick = {
            isChecked = !isChecked
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Text(if (isChecked) "Checked" else "Unchecked")
    }
}

// Custom Styled Button
@Composable
fun CustomStyledButton() {
    Button(
        onClick = { /* Handle click */ },
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan, contentColor = Color.White),
        elevation = ButtonDefaults.elevatedButtonElevation()
    ) {
        Text("Custom Button")
    }
}

// Column to display all button types
@Composable
private fun ButtonExamples() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Text Button", modifier = Modifier.padding(bottom = 8.dp))
        TextButtonExample()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Contained Button", modifier = Modifier.padding(bottom = 8.dp))
        ContainedButtonExample()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Outlined Button", modifier = Modifier.padding(bottom = 8.dp))
        OutlinedButtonExample()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Elevated Button", modifier = Modifier.padding(bottom = 8.dp))
        ElevatedButtonExample()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Icon Button", modifier = Modifier.padding(bottom = 8.dp))
        MyIconButton {  }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Filled Icon Button", modifier = Modifier.padding(bottom = 8.dp))
        FilledIconButtonExample()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Toggle Button", modifier = Modifier.padding(bottom = 8.dp))
        ToggleButtonExample()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Custom Styled Button", modifier = Modifier.padding(bottom = 8.dp))
        CustomStyledButton()
    }
}

// Preview Composable
@Preview(showBackground = true)
@Composable
private fun ButtonExamplesPreview() {
    AppTheme {
        ButtonExamples()
    }
}
