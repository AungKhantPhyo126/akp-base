plugins {
    alias(libs.plugins.custom.android.library)
}
android {
    namespace = "com.spotvnow.common"
}

dependencies{
    implementation (libs.androidx.biometric)
}