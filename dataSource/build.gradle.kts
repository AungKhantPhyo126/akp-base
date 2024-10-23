plugins {
    alias(libs.plugins.customPlugin.android.dataSource)
}

val passphrase: String = project.extra["Pass_Phrase"] as String
val baseUrl: String = project.extra["BASE_URL"] as String
val basicTk: String = project.extra["BASIC_TK"] as String
val baseUrlStag: String = project.extra["BASE_URL_STAG"] as String
val baseUrlUat: String = project.extra["BASE_URL_UAT"] as String
android {
    namespace = "com.dev.datasource"
    defaultConfig {
        buildConfigField ("String", "Passphrase", "\"$passphrase\"" )
        // Auth
        buildConfigField("String", "AUTH", "\"/auth\"")
        buildConfigField("String", "METHOD_POST_LOGIN", "\"/login\"")
        buildConfigField("String", "LOGIN_BIOMETRIC", "\"/loginbiometric\"")

    }
    buildTypes {
        getByName("debug") {
            ext.set("enableCrashlytics", false)
            ext.set("alwaysUpdateBuildId", false)
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"))
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("stage")
    flavorDimensions.add("uat")
    flavorDimensions.add("prod")

    productFlavors {
        // Build this if client/pm wants apk with staging endpoints
        // All three flavors use the same google-services.json
        create("staging") {
            dimension = "stage"
            buildConfigField("String", "BASIC_TK", "\"$basicTk\"")
            buildConfigField("String", "BASE_URL_STAG", "\"$baseUrlStag\"")
        }

        // Build this if client/pm wants apk with uat endpoints
        create("uat") {
            dimension = "uat"
            buildConfigField("String", "BASIC_TK", "\"$basicTk\"")
            buildConfigField("String", "BASE_URL_UAT", "\"$baseUrlUat\"")
        }

        // Build this if client/pm wants apk with production endpoints or
        // once client/pm finally approved to upload to playstore
        create("prod") {
            dimension = "prod"
            buildConfigField("String", "BASIC_TK", "\"$basicTk\"")
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }
    }
    buildFeatures {
        buildConfig = true
    }
}
dependencies{
    implementation(project(":common"))
    kapt(libs.androidx.room.compiler)
}