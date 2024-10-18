package com.dev.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.dev.convention.extension.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ComposeApplicationPlugin: Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<ApplicationExtension> {
                configureCompose(this)
            }
        }
    }
}
