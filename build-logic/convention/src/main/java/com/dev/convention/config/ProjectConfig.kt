package com.dev.convention.config

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

@Suppress("SpellCheckingInspection")
object ProjectConfig {
    val android = AndroidConfig(
        minSdkVersion = 24,
        targetSdkVersion = 34,
        compileSdkVersion = 34,
        applicationId = "com.dev.akp_base",
        versionCode = 1,
        versionName = "1.0",
        nameSpace = "com.dev.akp_base"
    )

    val jvm = JvmConfig(
        javaVersion = JavaVersion.VERSION_17,
        kotlinJvm = JvmTarget.JVM_17,
        freeCompilerArgs = listOf()
    )
}


data class AndroidConfig(
    val minSdkVersion: Int,
    val targetSdkVersion: Int,
    val compileSdkVersion: Int,
    val applicationId: String,
    val versionCode: Int,
    val versionName: String,
    val nameSpace: String
)


data class JvmConfig(
    val javaVersion: JavaVersion,
    val kotlinJvm: JvmTarget,
    val freeCompilerArgs: List<String>
)