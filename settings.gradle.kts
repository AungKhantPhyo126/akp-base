pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://maven.google.com") }
    }
}

rootProject.name = "akp-base"
include(":app")
include(":features:auth")
include(":designSystem")
//
include(":dataSource")
include(":common")
include(":features:unit_test")
