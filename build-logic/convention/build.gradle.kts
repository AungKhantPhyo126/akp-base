import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}


gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "customPlugin.android.application.compose"
            implementationClass = "plugin.AndroidApplicationPlugin"
        }

        register("androidLibrary") {
            id = "customPlugin.android.library"
            implementationClass = "com.dev.convention.plugin.AndroidLibraryConventionPlugin"
        }

        register("composeLibrary") {
            id = "customPlugin.compose.library"
            implementationClass = "com.dev.convention.plugin.ComposeLibraryConventionPlugin"
        }

        register("testLibrary") {
            id = "customPlugin.test.library"
            implementationClass = "com.dev.convention.plugin.TestLibraryConventionPlugin"
        }

    }
}