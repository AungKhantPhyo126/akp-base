package com.dev.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.dev.convention.config.ProjectConfig
import com.dev.convention.extension.configureAndroidKotlin
import com.dev.convention.extension.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidDataSourcePlugin:Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("com.google.dagger.hilt.android")
                apply("kotlin-kapt")

            }

            extensions.configure<LibraryExtension> {

                defaultConfig.apply {
                    lint.targetSdk = ProjectConfig.android.targetSdkVersion
                    minSdk = ProjectConfig.android.minSdkVersion
                }

                buildTypes {
                    getByName("debug") {
                        isMinifyEnabled = false
                    }
                    getByName("release") {
                        isMinifyEnabled = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }

                buildFeatures.buildConfig = true


                dependencies {
                    add("implementation", versionCatalog().findLibrary("hilt-android").get())
                    add("implementation", versionCatalog().findLibrary("kotlinx-serialization-json").get())
                    add("ksp", versionCatalog().findLibrary("hilt-compiler").get())
                    add("implementation", versionCatalog().findLibrary("arrow").get())
                    add("implementation", versionCatalog().findLibrary("retrofit-core").get())
                    add("implementation", versionCatalog().findLibrary("retrofit-kotlin-serialization").get())
                    add("implementation", versionCatalog().findLibrary("okhttp-logging").get())
                    add("debugImplementation", versionCatalog().findLibrary("chucker-debug").get())
                    add("releaseImplementation", versionCatalog().findLibrary("chucker-release").get())
                    add("implementation", versionCatalog().findLibrary("curl").get())

                    //Room
                    add("implementation", versionCatalog().findLibrary("androidx-dataStore-core").get())
                    add("implementation", versionCatalog().findLibrary("androidx-dataStore-pref").get())
                    add("implementation", versionCatalog().findLibrary("androidx-room-runtime").get())
                    add("implementation", versionCatalog().findLibrary("androidx-room-ktx").get())
                    add("implementation", versionCatalog().findLibrary("sqlcipher").get())

                }
                configureAndroidKotlin(this)
            }
        }
    }
}