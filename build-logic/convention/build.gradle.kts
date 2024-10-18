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
        register("androidApplication"){
            id = "customPlugin.android.application"
            implementationClass= "com.dev.convention.plugin.AndroidApplicationPlugin"
        }
        register("composeApplication") {
            id = "customPlugin.android.application.compose"
            implementationClass = "com.dev.convention.plugin.ComposeApplicationPlugin"
        }

        register("androidLibrary") {
            id = "customPluginLibrary.android.library"
            implementationClass = "com.dev.convention.plugin.AndroidLibraryConventionPlugin"
        }

        register("composeLibrary") {
            id = "customPluginLibrary.compose.library"
            implementationClass = "com.dev.convention.plugin.ComposeLibraryConventionPlugin"
        }

        register("testLibrary") {
            id = "customPlugin.test.library"
            implementationClass = "com.dev.convention.plugin.TestLibraryConventionPlugin"
        }

        register("androidDataSource") {
            id = "customPlugin.android.dataSource"
            implementationClass = "com.dev.convention.plugin.AndroidDataSourcePlugin"
        }

    }
}