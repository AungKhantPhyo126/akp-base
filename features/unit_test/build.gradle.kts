plugins {
    alias(libs.plugins.custom.compose.library)
    alias(libs.plugins.custom.android.library)
}

android {
    namespace = "com.dev.unit_test"
}

dependencies{
    implementation(project(":common"))
}
