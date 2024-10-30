package com.dev.designsystem.ui.components.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.dev.designsystem.ui.theme.AppTheme
import com.dev.designsystem.ui.theme.LocalSpacing
import com.dev.designsystem.ui.theme.dialogTitle


@Composable
fun MyAlertDialog(
    title: String = "",
    body: String = "",
    onDismissRequest: () -> Unit = {},
    confirmButtonText: String = "Okay",
    confirmButton: () -> Unit,
    dismissButtonText: String = "Cancel",
    dismissButton: (() -> Unit)? = null,
    properties: DialogProperties = DialogProperties()
) {
    AlertDialog(
        properties = properties,
        title = {
            if (title.isNotBlank()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.dialogTitle
                )
            }
        },
        text = {
            if (body.isNotBlank()) {
                Text(
                    text = body,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        onDismissRequest = onDismissRequest,
        dismissButton = {
            if (dismissButton != null) {
                TextButton(
                    onClick = dismissButton,
                    content = {
                        Text(dismissButtonText)
                    }
                )
            }
        },

        confirmButton = {
            TextButton(
                onClick = confirmButton,
                content = {
                    Text(confirmButtonText)
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAlertDialogWithButtonsInColumn(
    title: String = "",
    body: String = "",

    onDismissRequest: () -> Unit = {},
    confirmButtonText: String = "Okay",
    confirmButton: () -> Unit,
    dismissButtonText: String = "Cancel",
    dismissButton: (() -> Unit)? = null,
    properties: DialogProperties = DialogProperties()
) {
    BasicAlertDialog(
        modifier = Modifier.clip(AlertDialogDefaults.shape),
        properties = properties,
        content = {
            Surface() {
                Column(
                    modifier = Modifier.padding(LocalSpacing.current.space24),
                ) {
                    if (title.isNotBlank()) {
                        Text(
                            modifier = Modifier.padding(bottom = LocalSpacing.current.space16),
                            text = title,
                            style = MaterialTheme.typography.dialogTitle
                        )
                    }
                    if (body.isNotBlank()) {
                        Text(
                            text = body,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    Spacer(modifier = Modifier.height(LocalSpacing.current.space16))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (dismissButton != null) {
                            TextButton(
                                onClick = dismissButton,
                                content = {
                                    Text(dismissButtonText)
                                }
                            )
                        }
                        TextButton(
                            onClick = confirmButton,
                            content = {
                                Text(confirmButtonText)
                            }
                        )
                    }

                }
            }
        },
        onDismissRequest = onDismissRequest,
    )
}


@Preview(showBackground = true)
@Composable
private fun MyAlertDialogPreview() {
    AppTheme {
        MyAlertDialog(
            title = "”App” Wants to Use “google.com” to Sign In",
            body = "This allows the app and website to share information about you.",
            confirmButton = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyAlertDialogBodyOnlyPreview() {
    AppTheme {
        MyAlertDialog(
            body = "This allows the app and website to share information about you.",
            onDismissRequest = { },
            confirmButtonText = "OK",
            confirmButton = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyAlertDialogBothButtonPreview() {
    AppTheme {
        MyAlertDialog(
            title = "”APP” Wants to Use “google.com” to Sign In",
            body = "This allows the app and website to share information about you.",
            onDismissRequest = { },
            confirmButtonText = "Continue",
            dismissButtonText = "Cancel",
            confirmButton = {},
            dismissButton = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyAlertDialogWithButtonSizePreview() {
    AppTheme {
        MyAlertDialogWithButtonsInColumn(
            body = "If you try to change country code of your last migrated phone number which has premium valid status, the subscription will be set to free.",
            onDismissRequest = { },
            confirmButtonText = "Change",
            dismissButtonText = "Don't change",
            confirmButton = {},
            dismissButton = {}
        )
    }
}


