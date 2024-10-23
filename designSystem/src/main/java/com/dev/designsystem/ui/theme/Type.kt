package com.dev.designsystem.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.designsystem.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Poppins"),
        fontProvider = provider,
    )
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Poppins"),
        fontProvider = provider,
    )
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)

@Composable
fun TypographyPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Display Large", style = AppTypography.displayLarge, modifier = Modifier.padding(bottom = 8.dp))
        Text("Display Medium", style = AppTypography.displayMedium, modifier = Modifier.padding(bottom = 8.dp))
        Text("Display Small", style = AppTypography.displaySmall, modifier = Modifier.padding(bottom = 8.dp))
        Text("Headline Large", style = AppTypography.headlineLarge, modifier = Modifier.padding(bottom = 8.dp))
        Text("Headline Medium", style = AppTypography.headlineMedium, modifier = Modifier.padding(bottom = 8.dp))
        Text("Headline Small", style = AppTypography.headlineSmall, modifier = Modifier.padding(bottom = 8.dp))
        Text("Title Large", style = AppTypography.titleLarge, modifier = Modifier.padding(bottom = 8.dp))
        Text("Title Medium", style = AppTypography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
        Text("Title Small", style = AppTypography.titleSmall, modifier = Modifier.padding(bottom = 8.dp))
        Text("Body Large", style = AppTypography.bodyLarge, modifier = Modifier.padding(bottom = 8.dp))
        Text("Body Medium", style = AppTypography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp))
        Text("Body Small", style = AppTypography.bodySmall, modifier = Modifier.padding(bottom = 8.dp))
        Text("Label Large", style = AppTypography.labelLarge, modifier = Modifier.padding(bottom = 8.dp))
        Text("Label Medium", style = AppTypography.labelMedium, modifier = Modifier.padding(bottom = 8.dp))
        Text("Label Small", style = AppTypography.labelSmall, modifier = Modifier.padding(bottom = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TypographyPreviewScreen() {
    AppTheme {
        TypographyPreview()
    }
}