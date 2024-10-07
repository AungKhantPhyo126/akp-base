package com.dev.convention.extension

import com.android.build.api.dsl.CommonExtension
import com.dev.convention.config.ProjectConfig
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

internal fun Project.configureAndroidKotlin(
    extension:CommonExtension<*,*,*,*,*,*>
){
    with(extension){
        namespace = ProjectConfig.android.nameSpace
        compileSdk = ProjectConfig.android.compileSdkVersion
        defaultConfig{
            minSdk = ProjectConfig.android.minSdkVersion
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables.useSupportLibrary = true
        }
        compileOptions {
            sourceCompatibility = ProjectConfig.jvm.javaVersion
            targetCompatibility = ProjectConfig.jvm.javaVersion
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
        val libs = versionCatalog()
        dependencies {
            add("implementation", libs.findLibrary("androidx-core-ktx").get())
            add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
            add("testImplementation", libs.findLibrary("junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
            add("implementation", libs.findLibrary("androidx-appcompat").get())
            add("implementation", libs.findLibrary("androidx-activity-compose").get())
            add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
        }
        tasks.withType<KotlinJvmCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(ProjectConfig.jvm.kotlinJvm)
                freeCompilerArgs.addAll(ProjectConfig.jvm.freeCompilerArgs)
            }
        }
    }
}