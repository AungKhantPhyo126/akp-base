package com.dev.convention.plugin

import com.android.build.gradle.LibraryExtension
import com.dev.convention.extension.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class TestLibraryConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("de.mannodermaus.android-junit5")
            }

            extensions.configure<LibraryExtension> {
                @Suppress("UnstableApiUsage")
                testOptions {
                    unitTests {
                        isReturnDefaultValues = true
                        isIncludeAndroidResources = true
                    }
                }
            }

            dependencies {
                add("testImplementation", versionCatalog().findLibrary("jupiter-api").get())
                add("testRuntimeOnly", versionCatalog().findLibrary("jupiter-engine").get())
                add("testImplementation", versionCatalog().findLibrary("jupiter-params").get())
                add("testImplementation", versionCatalog().findLibrary("mockk").get())
                add("testImplementation", versionCatalog().findLibrary("mock-webserver").get())
                add("testImplementation", versionCatalog().findLibrary("assertk").get())
                add("testImplementation", versionCatalog().findLibrary("coroutines-test").get())
                add("testImplementation", versionCatalog().findLibrary("turbine").get())
            }
        }
    }
}