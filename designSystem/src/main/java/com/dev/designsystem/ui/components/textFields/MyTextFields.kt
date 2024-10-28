package com.dev.designsystem.ui.components.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.dev.designsystem.ui.components.animation.shake
import com.dev.designsystem.ui.components.modifierExt.conditional
import com.dev.designsystem.ui.theme.AppTheme
import com.dev.designsystem.ui.theme.spacing

@Composable
fun MyPinTextField(
    modifier: Modifier = Modifier,
    unFocusBorderColor: Color = Color.Transparent,
    focusBorderColor: Color = MaterialTheme.colorScheme.primary,
    otpCode: String = "",
    isError: Boolean? = null,
    isPassword: Boolean = false,
    requestFocus: Boolean = true,
    count: Int = 6,
    onComplete: (String, Boolean) -> Unit,
) {
    val focusRequester = remember {
        FocusRequester()
    }

    if (requestFocus) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }

    val error by remember(isError) {
        derivedStateOf {
            isError == true && otpCode.length == count
        }
    }

    BasicTextField(
        modifier = modifier
            .conditional(requestFocus) {
                focusRequester(focusRequester)
            }
            .fillMaxWidth()
            .background(Color.Transparent),
        value = otpCode,
        onValueChange = {
            if (it.length <= count && it.isDigitsOnly()) {
                onComplete(it, it.length == count)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(count) {
                OtpText(
                    direction = Direction.TOP,
                    isError = error,
                    modifier = Modifier
                        .width(48.dp)
                        .height(56.dp)
                        .shake(error),
                    text = if (it < otpCode.length) {
                        if (isPassword) "\u2022" else otpCode[it].toString()
                    } else {
                        ""
                    },
                    showCursor = it == otpCode.length,
                    focusBorderColor = if (it == otpCode.length) focusBorderColor else unFocusBorderColor,
                    unFocusBorderColor = unFocusBorderColor,
                )

                Spacer(modifier = Modifier.width(spacing.space8))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OtpSlotPreview() {
    AppTheme  {
        MyPinTextField(count = 4, isError = false) { a, b -> }
    }
}


