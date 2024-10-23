plugins {
    alias(libs.plugins.custom.compose.library)
    alias(libs.plugins.custom.android.library)
}

android {
    namespace = "com.dev.designsystem"
}

dependencies {

    implementation(libs.androidx.ui.text.google.fonts)
}