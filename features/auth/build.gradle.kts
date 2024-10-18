plugins {
    alias(libs.plugins.custom.compose.library)
    alias(libs.plugins.custom.android.library)
}

android {
    namespace = "com.dev.auth"
}

dependencies{
    implementation(project(":common"))
}
