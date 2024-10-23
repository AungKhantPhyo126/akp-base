plugins {
    alias(libs.plugins.customPlugin.android.application.compose)
    alias(libs.plugins.customPlugin.android.application)
}

android {
    namespace = "com.dev.akp_base"

}

dependencies {
    implementation(project(":designSystem"))
    implementation(project(":dataSource"))
    implementation(project(":common"))
    implementation(project(":features:auth"))
}